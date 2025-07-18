import ru.rtk.homework9.Tv;

public class App {
    public static void main(String[] args) {
        Tv tv1 = new Tv();
        tv1.setVendorName("Xiaomi");
        tv1.setModelName("MI TV A 32 2025");
        tv1.setDescription("Телевизор Xiaomi");
        tv1.setHd("HD");
        tv1.setDiagonal(32);
        tv1.setPrice(16100);

        Tv tv2 = new Tv("Samsung", "UE55DU7100UXRU");
        tv2.setDescription("Телевизор Samsung");
        tv2.setHd("4K UHD");
        tv2.setDiagonal(55);
        tv2.setPrice(58900);

        tv1.showFullInfo();
        tv2.showShortInfo();

        Tv tv3 = new Tv(
                "Digma Яндекс",
                "DM-LED43SBB33",
                "Телевизор Digma",
                "Full HD",
                43,
                24400
        );
        tv3.changeParams();
    }
}
