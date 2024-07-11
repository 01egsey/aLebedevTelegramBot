package ru.aLebedev.tgBot.botDB.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

import jakarta.annotation.PostConstruct;
import ru.aLebedev.tgBot.botDB.entity.Client;
import ru.aLebedev.tgBot.botDB.entity.ClientOrder;
import ru.aLebedev.tgBot.botDB.entity.OrderProduct;
import ru.aLebedev.tgBot.botDB.entity.Product;
import ru.aLebedev.tgBot.botDB.repository.ClientOrderRepository;
import ru.aLebedev.tgBot.botDB.repository.ClientRepository;
import ru.aLebedev.tgBot.botDB.repository.OrderProductRepository;

@Service
public class TelegramBotConnection {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientOrderRepository clientOrderRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private EntitiesService entitiesService;

    private TelegramBot bot;
    private Long currentCategoryId = null;

    private class TelegramUpdatesListener implements UpdatesListener {
        Double totalPriceCounter = 0.0;
        List<Product> allProducts = new ArrayList<>();

        @Override
        public int process(List<Update> updates) {
            updates.forEach(this::processUpdate);
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }

        private void processUpdate(Update update) {

            if (update.callbackQuery() != null) {
                handleCallbackQuery(update);
            } else if (update.message() != null) {
                handleMessage(update.message());
            }
        }

        private void handleCallbackQuery(Update update) {
            String productId = update.callbackQuery().data();

            Product product = entitiesService.findProductById(Long.parseLong(productId));
            if (product != null) {
                totalPriceCounter += product.getPrice();
                allProducts.add(product);
                bot.execute(new SendMessage(update.callbackQuery().message().chat().id(),
                        "Вы выбрали продукт: " + product.getName()));

                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setProduct(product);
                orderProduct
                        .setClientOrder(entitiesService.findClientOrderByClient_Id(update.callbackQuery().from().id()));
                orderProduct.setCountProduct(1);
                orderProductRepository.save(orderProduct);

            } else {
                bot.execute(new SendMessage(update.callbackQuery().message().chat().id(), "Продукт не найден."));
            }
        }

        private void handleMessage(Message message) {
            if (message.from() == null) {
                bot.execute(new SendMessage(message.chat().id(), "Ошибка: пользователь не определен."));
                return;
            }

            // Добавление клиента в базу
            Client client = clientRepository.findByExternalId(message.from().id());
            if (client == null) {
                client = new Client();
                client.setExternalId(message.from().id());
                client.setFullName(message.from().firstName() + " " + message.from().lastName());
                client.setPhoneNumber(getPhoneNumber(message));
                client.setAddress("Sevas");
                clientRepository.save(client);
            }

            // Добавление Клиентского заказа в базу
            ClientOrder clientOrder = entitiesService.findClientOrderByClient_Id(message.from().id());
            if (clientOrder == null) {
                clientOrder = new ClientOrder();
                clientOrder.setStatus(1);
                clientOrder.setClient(client);
                clientOrder.setTotal(0.0);
                clientOrderRepository.save(clientOrder);
            }

            // Пользовательский интерфейс
            processUserMessage(message, clientOrder);
        }

        private String getPhoneNumber(Message message) {
            if (message.contact() != null && message.contact().phoneNumber() != null) {
                String phoneNumber = message.contact().phoneNumber();
                return phoneNumber.length() > 15 ? "Wrong num" : phoneNumber;
            }
            return "Нет номера";
        }

        private void processUserMessage(Message message, ClientOrder clientOrder) {

            switch (message.text()) {
                case "Напитки" -> currentCategoryId = 4L;
                case "Бургеры" -> currentCategoryId = 3L;
                case "В основное меню" -> currentCategoryId = null;
                case "Пицца" -> currentCategoryId = 1L;
                case "Роллы" -> currentCategoryId = 2L;
                case "Газированные напитки" -> currentCategoryId = 11L;
                case "Энергетические напитки" -> currentCategoryId = 12L;
                case "Соки" -> currentCategoryId = 13L;
                case "Другие напитки" -> currentCategoryId = 14L;
                case "Классические бургеры" -> currentCategoryId = 9L;
                case "Острые бургеры" -> currentCategoryId = 10L;
                case "Классические роллы" -> currentCategoryId = 5L;
                case "Запеченные роллы" -> currentCategoryId = 6L;
                case "Сладкие роллы" -> currentCategoryId = 7L;
                case "Наборы" -> currentCategoryId = 8L;
                case "Оформить заказ" -> {
                    clientOrder.setTotal(totalPriceCounter);
                    clientOrder.setStatus(2);
                    clientOrderRepository.save(clientOrder);
                    bot.execute(new SendMessage(message.chat().id(),
                            "Заказ принят, общая сумма - " + totalPriceCounter));
                    if (!allProducts.isEmpty()) {
                        StringBuilder productsMessage = new StringBuilder("Выбранные продукты:\n");
                        for (Product product : allProducts) {
                            productsMessage.append(product.getName())
                                    .append(" - ")
                                    .append(product.getPrice())
                                    .append(" руб.\n");
                        }
                        bot.execute(new SendMessage(message.chat().id(), productsMessage.toString()));
                    } else {
                        bot.execute(new SendMessage(message.chat().id(), "Список выбранных продуктов пуст."));
                    }
                    currentCategoryId = -1L;
                }
                default -> bot.execute(new SendMessage(message.chat().id(), "Я не знаю, что вы имеете в виду."));
            }

            sendCategoriesKeyboard(message.chat().id());
            if (currentCategoryId != null) {
                showProducts(currentCategoryId, message.chat().id());
            }
        }

        private void sendCategoriesKeyboard(Long chatId) {
            List<KeyboardButton> categories = entitiesService.findCategoriesByParentId(currentCategoryId)
                    .stream()
                    .map(category -> new KeyboardButton(category.getName()))
                    .collect(Collectors.toList());

            ReplyKeyboardMarkup replyMarkup = new ReplyKeyboardMarkup(
                    categories.toArray(new KeyboardButton[0]))
                    .resizeKeyboard(true)
                    .addRow(new KeyboardButton("Оформить заказ"))
                    .addRow(new KeyboardButton("В основное меню"));

            bot.execute(new SendMessage(chatId, "Категории").replyMarkup(replyMarkup));
        }

        private void showProducts(Long categoryId, Long chatId) {
            List<Product> products = entitiesService.getProductsByCategoryId(categoryId);
            if (!products.isEmpty()) {
                InlineKeyboardMarkup inlineMarkup = new InlineKeyboardMarkup();
                for (Product product : products) {
                    InlineKeyboardButton button = new InlineKeyboardButton(
                            String.format("Товар %s. Цена %.2f руб.", product.getName(), product.getPrice()))
                            .callbackData(product.getId().toString());
                    inlineMarkup.addRow(button);
                }
                bot.execute(new SendMessage(chatId, "Товары:").replyMarkup(inlineMarkup));
            }
        }
    }

    @PostConstruct
    public void createConnection() {
        bot = new TelegramBot("7159938018:AAEAopGGtVc7Q5_ydb_hDHau9jY8YdnLJmU");
        bot.setUpdatesListener(new TelegramUpdatesListener());
    }
}
