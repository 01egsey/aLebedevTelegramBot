package ru.aLebedev.tgBot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.aLebedev.tgBot.botDB.entity.Category;
import ru.aLebedev.tgBot.botDB.entity.Client;
import ru.aLebedev.tgBot.botDB.entity.ClientOrder;
import ru.aLebedev.tgBot.botDB.entity.OrderProduct;
import ru.aLebedev.tgBot.botDB.entity.Product;
import ru.aLebedev.tgBot.botDB.repository.CategoryRepository;
import ru.aLebedev.tgBot.botDB.repository.ClientOrderRepository;
import ru.aLebedev.tgBot.botDB.repository.ClientRepository;
import ru.aLebedev.tgBot.botDB.repository.OrderProductRepository;
import ru.aLebedev.tgBot.botDB.repository.ProductRepository;

@SpringBootTest
public class FillingTests {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ClientOrderRepository clientOrderRepository;

    @Test
    public void testForDatabase() {
        /////////////////// Объекты класса Category/////////////////////

        // Классические категори
        Category pizza = new Category();
        // pizza.setId(1001L);
        pizza.setName("Пицца");
        pizza.setParent(null);
        categoryRepository.save(pizza);

        Category rolls = new Category();
        // rolls.setId(1002L);
        rolls.setName("Роллы");
        rolls.setParent(null);
        categoryRepository.save(rolls);

        Category burger = new Category();
        // burger.setId(1003L);
        burger.setName("Бургеры");
        burger.setParent(null);
        categoryRepository.save(burger);

        Category drink = new Category();
        // drink.setId(1004L);
        drink.setName("Напитки");
        drink.setParent(null);
        categoryRepository.save(drink);

        // Роллы
        Category rollClassic = new Category();
        rollClassic.setName("Классические роллы");
        rollClassic.setParent(rolls);
        categoryRepository.save(rollClassic);

        Category rollBaked = new Category();
        rollBaked.setName("Запеченные роллы");
        rollBaked.setParent(rolls);
        categoryRepository.save(rollBaked);

        Category rollSweet = new Category();
        rollSweet.setName("Сладкие роллы");
        rollSweet.setParent(rolls);
        categoryRepository.save(rollSweet);

        Category rollSet = new Category();
        rollSet.setName("Наборы");
        rollSet.setParent(rolls);
        categoryRepository.save(rollSet);

        // Бургеры

        Category burgerClassic = new Category();
        burgerClassic.setName("Классические бургеры");
        burgerClassic.setParent(burger);
        categoryRepository.save(burgerClassic);

        Category burgerHot = new Category();
        burgerHot.setName("Острые бургеры");
        burgerHot.setParent(burger);
        categoryRepository.save(burgerHot);

        // Напитки

        Category drinkFizzy = new Category();
        drinkFizzy.setName("Газированные напитки");
        drinkFizzy.setParent(drink);
        categoryRepository.save(drinkFizzy);

        Category drinkEnergy = new Category();
        drinkEnergy.setName("Энергетические напитки");
        drinkEnergy.setParent(drink);
        categoryRepository.save(drinkEnergy);

        Category drinkJuice = new Category();
        drinkJuice.setName("Соки");
        drinkJuice.setParent(drink);
        categoryRepository.save(drinkJuice);

        Category drinkOther = new Category();
        drinkOther.setName("Другие напитки");
        drinkOther.setParent(drink);
        categoryRepository.save(drinkOther);

        /////////////////// Объекты класса Product/////////////////////

        // Пицца
        Product pizza1 = new Product();
        pizza1.setCategory(pizza);
        pizza1.setName("Цезарь");
        pizza1.setPrice(500.99);
        pizza1.setDescription("Цыпленок, салат Айсберг, томаты черри, моцарелла, пармезан, соус Цезарь и Рэнч");
        productRepository.save(pizza1);

        Product pizza2 = new Product();
        pizza2.setCategory(pizza);
        pizza2.setName("Маргарита");
        pizza2.setPrice(450.99);
        pizza2.setDescription("Томаты, моцарелла, базилик, оливковое масло");
        productRepository.save(pizza2);

        Product pizza3 = new Product();
        pizza3.setCategory(pizza);
        pizza3.setName("Пепперони");
        pizza3.setPrice(550.99);
        pizza3.setDescription("Пепперони, томаты, моцарелла, соус Маринара");
        productRepository.save(pizza3);

        // Классические роллы
        Product rollClassic1 = new Product();
        rollClassic1.setCategory(rollClassic);
        rollClassic1.setName("Филадельфия");
        rollClassic1.setPrice(350.99);
        rollClassic1.setDescription("Лосось, сливочный сыр, огурец, авокадо, рис, нори");
        productRepository.save(rollClassic1);

        Product rollClassic2 = new Product();
        rollClassic2.setCategory(rollClassic);
        rollClassic2.setName("Калифорния");
        rollClassic2.setPrice(300.99);
        rollClassic2.setDescription("Краб, авокадо, огурец, икра тобико, рис, нори");
        productRepository.save(rollClassic2);

        Product rollClassic3 = new Product();
        rollClassic3.setCategory(rollClassic);
        rollClassic3.setName("Дракон");
        rollClassic3.setPrice(400.99);
        rollClassic3.setDescription("Угорь, авокадо, огурец, сливочный сыр, рис, нори, соус Унаги");
        productRepository.save(rollClassic3);

        // 3апеченные роллы
        Product rollBaked1 = new Product();
        rollBaked1.setCategory(rollBaked);
        rollBaked1.setName("Запеченный краб");
        rollBaked1.setPrice(370.99);
        rollBaked1.setDescription("Краб, сливочный сыр, огурец, рис, нори, соус Спайси");
        productRepository.save(rollBaked1);

        Product rollBaked2 = new Product();
        rollBaked2.setCategory(rollBaked);
        rollBaked2.setName("Запеченный угорь");
        rollBaked2.setPrice(390.99);
        rollBaked2.setDescription("Угорь, сливочный сыр, огурец, рис, нори, соус Унаги");
        productRepository.save(rollBaked2);

        Product rollBaked3 = new Product();
        rollBaked3.setCategory(rollBaked);
        rollBaked3.setName("Запеченный лосось");
        rollBaked3.setPrice(410.99);
        rollBaked3.setDescription("Лосось, сливочный сыр, авокадо, рис, нори, соус Спайси");
        productRepository.save(rollBaked3);

        // Сладкие роллы
        Product rollSweet1 = new Product();
        rollSweet1.setCategory(rollSweet);
        rollSweet1.setName("Фруктовый микс");
        rollSweet1.setPrice(320.99);
        rollSweet1.setDescription("Киви, банан, клубника, манго, рис, нори, сливочный сыр");
        productRepository.save(rollSweet1);

        Product rollSweet2 = new Product();
        rollSweet2.setCategory(rollSweet);
        rollSweet2.setName("Шоколадный ролл");
        rollSweet2.setPrice(330.99);
        rollSweet2.setDescription("Шоколад, банан, сливочный сыр, рис, нори");
        productRepository.save(rollSweet2);

        Product rollSweet3 = new Product();
        rollSweet3.setCategory(rollSweet);
        rollSweet3.setName("Карамельный ролл");
        rollSweet3.setPrice(340.99);
        rollSweet3.setDescription("Карамель, яблоко, сливочный сыр, рис, нори");
        productRepository.save(rollSweet3);

        // Наборы
        Product rollSet1 = new Product();
        rollSet1.setCategory(rollSet);
        rollSet1.setName("Сет классический");
        rollSet1.setPrice(1200.99);
        rollSet1.setDescription("Ассорти из Филадельфии, Калифорнии и Дракона");
        productRepository.save(rollSet1);

        Product rollSet2 = new Product();
        rollSet2.setCategory(rollSet);
        rollSet2.setName("Сет запеченный");
        rollSet2.setPrice(1300.99);
        rollSet2.setDescription("Ассорти из запеченного краба, угря и лосося");
        productRepository.save(rollSet2);

        Product rollSet3 = new Product();
        rollSet3.setCategory(rollSet);
        rollSet3.setName("Сет сладкий");
        rollSet3.setPrice(1100.99);
        rollSet3.setDescription("Ассорти из фруктового микса, шоколадного и карамельного роллов");
        productRepository.save(rollSet3);

        // Классические бургеры
        Product burgerClassic1 = new Product();
        burgerClassic1.setCategory(burgerClassic);
        burgerClassic1.setName("Чизбургер");
        burgerClassic1.setPrice(250.99);
        burgerClassic1.setDescription("Говядина, сыр Чеддер, салат, томаты, соус, булочка");
        productRepository.save(burgerClassic1);

        Product burgerClassic2 = new Product();
        burgerClassic2.setCategory(burgerClassic);
        burgerClassic2.setName("Гамбургер");
        burgerClassic2.setPrice(230.99);
        burgerClassic2.setDescription("Говядина, салат, томаты, соус, булочка");
        productRepository.save(burgerClassic2);

        Product burgerClassic3 = new Product();
        burgerClassic3.setCategory(burgerClassic);
        burgerClassic3.setName("Двойной чизбургер");
        burgerClassic3.setPrice(350.99);
        burgerClassic3.setDescription("Двойная говядина, двойной сыр Чеддер, салат, томаты, соус, булочка");
        productRepository.save(burgerClassic3);

        // Острые бургеры
        Product burgerHot1 = new Product();
        burgerHot1.setCategory(burgerHot);
        burgerHot1.setName("Острый чили бургер");
        burgerHot1.setPrice(280.99);
        burgerHot1.setDescription("Говядина, сыр Чеддер, салат, томаты, острый соус чили, булочка");
        productRepository.save(burgerHot1);

        Product burgerHot2 = new Product();
        burgerHot2.setCategory(burgerHot);
        burgerHot2.setName("Мексиканский бургер");
        burgerHot2.setPrice(300.99);
        burgerHot2.setDescription("Говядина, сыр, салат, томаты, халапеньо, мексиканский соус, булочка");
        productRepository.save(burgerHot2);

        Product burgerHot3 = new Product();
        burgerHot3.setCategory(burgerHot);
        burgerHot3.setName("Огненный бургер");
        burgerHot3.setPrice(320.99);
        burgerHot3.setDescription("Говядина, острый сыр, салат, томаты, перец чили, острый соус, булочка");
        productRepository.save(burgerHot3);

        // Газированные напитки
        Product drinkFizzy1 = new Product();
        drinkFizzy1.setCategory(drinkFizzy);
        drinkFizzy1.setName("Кола");
        drinkFizzy1.setPrice(100.99);
        drinkFizzy1.setDescription("Классическая кола");
        productRepository.save(drinkFizzy1);

        Product drinkFizzy2 = new Product();
        drinkFizzy2.setCategory(drinkFizzy);
        drinkFizzy2.setName("Фанта");
        drinkFizzy2.setPrice(100.99);
        drinkFizzy2.setDescription("Апельсиновая фанта");
        productRepository.save(drinkFizzy2);

        Product drinkFizzy3 = new Product();
        drinkFizzy3.setCategory(drinkFizzy);
        drinkFizzy3.setName("Спрайт");
        drinkFizzy3.setPrice(100.99);
        drinkFizzy3.setDescription("Лимонно-лаймовый спрайт");
        productRepository.save(drinkFizzy3);

        // Энергетические напитки
        Product drinkEnergy1 = new Product();
        drinkEnergy1.setCategory(drinkEnergy);
        drinkEnergy1.setName("Red Bull");
        drinkEnergy1.setPrice(150.99);
        drinkEnergy1.setDescription("Классический энергетический напиток");
        productRepository.save(drinkEnergy1);

        Product drinkEnergy2 = new Product();
        drinkEnergy2.setCategory(drinkEnergy);
        drinkEnergy2.setName("Monster");
        drinkEnergy2.setPrice(150.99);
        drinkEnergy2.setDescription("Энергетический напиток Monster");
        productRepository.save(drinkEnergy2);

        Product drinkEnergy3 = new Product();
        drinkEnergy3.setCategory(drinkEnergy);
        drinkEnergy3.setName("Burn");
        drinkEnergy3.setPrice(150.99);
        drinkEnergy3.setDescription("Энергетический напиток Burn");
        productRepository.save(drinkEnergy3);

        // Соки
        Product drinkJuice1 = new Product();
        drinkJuice1.setCategory(drinkJuice);
        drinkJuice1.setName("Апельсиновый сок");
        drinkJuice1.setPrice(120.99);
        drinkJuice1.setDescription("Свежевыжатый апельсиновый сок");
        productRepository.save(drinkJuice1);

        Product drinkJuice2 = new Product();
        drinkJuice2.setCategory(drinkJuice);
        drinkJuice2.setName("Яблочный сок");
        drinkJuice2.setPrice(120.99);
        drinkJuice2.setDescription("Свежевыжатый яблочный сок");
        productRepository.save(drinkJuice2);

        Product drinkJuice3 = new Product();
        drinkJuice3.setCategory(drinkJuice);
        drinkJuice3.setName("Вишневый сок");
        drinkJuice3.setPrice(120.99);
        drinkJuice3.setDescription("Свежевыжатый вишневый сок");
        productRepository.save(drinkJuice3);

        // Другие напитки
        Product drinkOther1 = new Product();
        drinkOther1.setCategory(drinkOther);
        drinkOther1.setName("Вода");
        drinkOther1.setPrice(50.99);
        drinkOther1.setDescription("Бутылированная вода");
        productRepository.save(drinkOther1);

        Product drinkOther2 = new Product();
        drinkOther2.setCategory(drinkOther);
        drinkOther2.setName("Чай");
        drinkOther2.setPrice(70.99);
        drinkOther2.setDescription("Черный чай");
        productRepository.save(drinkOther2);

        Product drinkOther3 = new Product();
        drinkOther3.setCategory(drinkOther);
        drinkOther3.setName("Кофе");
        drinkOther3.setPrice(100.99);
        drinkOther3.setDescription("Черный кофе");
        productRepository.save(drinkOther3);

        // Клиенты
        Client client1 = new Client();
        client1.setAddress("address1");
        client1.setExternalId(1L);
        client1.setFullName("fullName1");
        client1.setPhoneNumber("+1111111111111");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setAddress("address2");
        client2.setExternalId(2L);
        client2.setFullName("fullName2");
        client2.setPhoneNumber("+2222222222222");
        clientRepository.save(client2);

        Client client3 = new Client();
        client3.setAddress("address3");
        client3.setExternalId(3L);
        client3.setFullName("fullName3");
        client3.setPhoneNumber("+3333333333333");
        clientRepository.save(client3);

        Client client4 = new Client();
        client4.setAddress("address4");
        client4.setExternalId(4L);
        client4.setFullName("fullName4");
        client4.setPhoneNumber("+4444444444444");
        clientRepository.save(client4);

        Client client5 = new Client();
        client5.setAddress("address5");
        client5.setExternalId(5L);
        client5.setFullName("fullName5");
        client5.setPhoneNumber("+5555555555555");
        clientRepository.save(client5);

        // Создание заказов для каждого клиента
        ClientOrder order1 = new ClientOrder();
        order1.setClient(client1);
        order1.setTotal(1200.00);
        order1.setStatus(1);
        clientOrderRepository.save(order1);

        ClientOrder order2 = new ClientOrder();
        order2.setClient(client1);
        order2.setTotal(800.00);
        order2.setStatus(1);
        clientOrderRepository.save(order2);

        ClientOrder order3 = new ClientOrder();
        order3.setClient(client2);
        order3.setTotal(1500.00);
        order3.setStatus(1);
        clientOrderRepository.save(order3);

        ClientOrder order4 = new ClientOrder();
        order4.setClient(client3);
        order4.setTotal(900.00);
        order4.setStatus(1);
        clientOrderRepository.save(order4);

        ClientOrder order5 = new ClientOrder();
        order5.setClient(client3);
        order5.setTotal(1100.00);
        order5.setStatus(1);
        clientOrderRepository.save(order5);

        // Наполнение заказов продуктами

        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setClientOrder(order1);
        orderProduct1.setProduct(pizza1);
        orderProduct1.setCountProduct(2);
        orderProductRepository.save(orderProduct1);

        OrderProduct orderProduct2 = new OrderProduct();
        orderProduct2.setClientOrder(order1);
        orderProduct2.setProduct(rollClassic1);
        orderProduct2.setCountProduct(3);
        orderProductRepository.save(orderProduct2);

        OrderProduct orderProduct3 = new OrderProduct();
        orderProduct3.setClientOrder(order2);
        orderProduct3.setProduct(burgerClassic1);
        orderProduct3.setCountProduct(1);
        orderProductRepository.save(orderProduct3);

        OrderProduct orderProduct4 = new OrderProduct();
        orderProduct4.setClientOrder(order3);
        orderProduct4.setProduct(drinkFizzy1);
        orderProduct4.setCountProduct(2);
        orderProductRepository.save(orderProduct4);

        OrderProduct orderProduct5 = new OrderProduct();
        orderProduct5.setClientOrder(order4);
        orderProduct5.setProduct(rollSweet1);
        orderProduct5.setCountProduct(2);
        orderProductRepository.save(orderProduct5);

        OrderProduct orderProduct6 = new OrderProduct();
        orderProduct6.setClientOrder(order5);
        orderProduct6.setProduct(drinkJuice1);
        orderProduct6.setCountProduct(1);
        orderProductRepository.save(orderProduct6);
    }

}
