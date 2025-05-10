import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Project : Aplikasi Reservasi Hotel
 * Author: Dede Irwanto
 * Email: id.dedeirwanto@yahoo.com
 * Telegram : @dedeirwanto
 * Date: 02/05/2025
 * Time: 03:38 AM
 */
public class Main {
    // array data kamar hotel
    private final Kamar[] dataKamarHotel = new Kamar[20];
    private int jumlahKamarHotel = 0;

    // array data reservasi kamar
    private final ReservasiKamar[] dataReservasiKamar = new ReservasiKamar[20];
    private int jumlahReservasiKamar = 0;

    // total biaya reservasi sebelum pajak dan biaya layanan
    double totalBiayaReservasiSebelumDiskonDanBiayaTambahan = 0;

    // fungsi scanner
    private String scanner(String message) {
        System.out.print(message + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // fungsi convert double ke format rupiah
    private String formatRupiah(double harga) {
        Locale indonesia = Locale.forLanguageTag("id-ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(indonesia);
        return formatRupiah.format(harga);
    }

    /**
     * awal fungsi input data kamar
     **/

    // fungsi tampilkan input data kamar
    private void tampilkanMenuInputDataKamar() {
        System.out.println("-----------------------------------------");
        System.out.println("INPUT DATA KAMAR - APLIKASI RESERVASI HOTEL");
        System.out.println("-----------------------------------------");
        inputDataKamar(0);
    }

    // fungsi input data kamar
    private void inputDataKamar(int indexDataKamar) {
        Kamar kamar = new Kamar();

        String inputNomorKamar = scanner("Nomor kamar");
        kamar.nomor = Integer.parseInt(inputNomorKamar);

        String inputTipeKamar = scanner("Tipe kamar (Standar/Superior/Deluxe/Suite)");
        TipeKamar.tipeKamar tipeKamar = TipeKamar.tipeKamar.valueOf(inputTipeKamar.toUpperCase());

        if (tipeKamar == TipeKamar.tipeKamar.STANDAR) {
            kamar.tipe = kamar.tipeStandar();
        } else if (tipeKamar == TipeKamar.tipeKamar.SUPERIOR) {
            kamar.tipe = kamar.tipeSuperior();
        } else if (tipeKamar == TipeKamar.tipeKamar.DELUXE) {
            kamar.tipe = kamar.tipeDeluxe();
        } else if (tipeKamar == TipeKamar.tipeKamar.SUITE) {
            kamar.tipe = kamar.tipeSuite();
        } else {
            System.out.println("Tipe kamar tidak tersedia!");
            kembaliKeMenuUtama();
        }
        kamar.tersedia = true;

        dataKamarHotel[indexDataKamar] = kamar;
        System.out.println("Kamar berhasil ditambahkan!");
        jumlahKamarHotel++;
        indexDataKamar++;
        tampilkanDataKamar(0);
        inputUlangDataKamar(indexDataKamar);
    }

    // fungsi input ulang data kamar
    private void inputUlangDataKamar(int indexDataKamar) {
        String inputUlang = scanner("Input ulang data kamar? [y/t]");

        if (inputUlang.equalsIgnoreCase("y")) {
            inputDataKamar(indexDataKamar);
        } else if (inputUlang.equalsIgnoreCase("t")) {
            tampilkanMenu();
        } else {
            System.out.println("Input tidak valid!, masukkan 'y' untuk ya atau 't' untuk tidak.");
            inputUlangDataKamar(indexDataKamar);
        }
    }

    /**
     * akhir semua fungsi input data kamar
     **/


    // fungsi tampilkan data kamar
    private void tampilkanDataKamar(int indexDataKamar) {
        if (indexDataKamar < jumlahKamarHotel) {
            String hargaPermalam = formatRupiah(dataKamarHotel[indexDataKamar].tipe.hargaPermalam);

            System.out.println((indexDataKamar + 1) + "| Nomor kamar: " + dataKamarHotel[indexDataKamar].nomor + ", Tipe kamar: " + dataKamarHotel[indexDataKamar].tipe.nama + ", Harga permalam: " + hargaPermalam + ", Status kamar: " + ((dataKamarHotel[indexDataKamar].tersedia) ? "Tersedia" : "Tidak Tersedia"));
            tampilkanDataKamar(indexDataKamar + 1);
        }
    }

    private void tampilkanDataReservasiKamar(int indexDataReservasiKamar) {
        if (indexDataReservasiKamar < jumlahReservasiKamar) {

            String hargaPermalam = formatRupiah(dataReservasiKamar[indexDataReservasiKamar].kamar.tipe.hargaPermalam);
            String totalBiaya = formatRupiah(dataReservasiKamar[indexDataReservasiKamar].totalHargaKamar);
            System.out.println((indexDataReservasiKamar + 1) + "| Nomor kamar: " + dataReservasiKamar[indexDataReservasiKamar].kamar.nomor + ", Harga permalam: " + hargaPermalam + ", Lama menginap: " + dataReservasiKamar[indexDataReservasiKamar].lamaMenginap + " malam, Total harga kamar: " + totalBiaya);
            tampilkanDataReservasiKamar(indexDataReservasiKamar + 1);
        }
    }

    /**
     * awal fungsi terima dan olah reservasi
     **/

    // fungsi tampilkan menu pesan kamar
    private void tampilkanMenuPesanKamar() {
        System.out.println("-----------------------------------------");
        System.out.println("PESAN KAMAR - APLIKASI RESERVASI HOTEL");
        System.out.println("-----------------------------------------");
        tampilkanDataKamar(0);
        pesanKamar();
    }

    private void pesanKamar() {
        String inputNomorKamar = scanner("Nomor kamar");
        String inputLamaMenginap = scanner("Lama menginap (malam)");

        int nomorKamar = Integer.parseInt(inputNomorKamar);
        int lamaMenginap = Integer.parseInt(inputLamaMenginap);
        olahReservasiKamar(nomorKamar, lamaMenginap, 0, jumlahReservasiKamar);
    }

    // fungsi pesan kamar
    private void olahReservasiKamar(int nomorKamar, int lamaMenginap, int indexDataKamar, int indexDataReservasiKamar) {
        if (indexDataKamar < jumlahKamarHotel) {
            if (dataKamarHotel[indexDataKamar].nomor == nomorKamar) {
                if (dataKamarHotel[indexDataKamar].tersedia) {
                    if (lamaMenginap >= 1) {
                        ReservasiKamar reservasiKamar = new ReservasiKamar();
                        reservasiKamar.kamar = dataKamarHotel[indexDataKamar];
                        reservasiKamar.lamaMenginap = lamaMenginap;
                        reservasiKamar.totalHargaKamar = reservasiKamar.lamaMenginap * reservasiKamar.kamar.tipe.hargaPermalam;
                        dataReservasiKamar[indexDataReservasiKamar] = reservasiKamar;
                        dataKamarHotel[indexDataKamar].tersedia = false;
                        System.out.println("Kamar berhasil dipesan!");
                        tampilkanDataKamar(0);
                        jumlahReservasiKamar++;
                        pesanUlangKamar();
                    }
                } else {
                    System.out.println("Nomor kamar tidak tersedia atau sudah dipesan!");
                    pesanUlangKamar();
                }
            } else {
                olahReservasiKamar(nomorKamar, lamaMenginap, indexDataKamar + 1, indexDataReservasiKamar);
            }
        }
    }

    private void pesanUlangKamar() {
        String pesanUlang = scanner("Pesan ulang kamar? [y/t]");
        if (pesanUlang.equalsIgnoreCase("y")) {
            if (jumlahReservasiKamar < 3) {
                pesanKamar();
            } else {
                System.out.println("Reservasi kamar maksimal 3!");
                kembaliKeMenuUtama();
            }
        } else if (pesanUlang.equalsIgnoreCase("t")) {
            kembaliKeMenuUtama();
        } else {
            System.out.println("Input tidak valid!, masukkan 'y' untuk ya atau 't' untuk tidak.");
            pesanUlangKamar();
        }
    }

    /**
     * awal fungsi cetak struk reservasi
     */
    private void tampilkanMenuCetakStrukReservasi() {
        System.out.println("------------------------------------------------");
        System.out.println("STRUK RESERVASI - APLIKASI RESERVASI HOTEL");
        System.out.println("------------------------------------------------");
        System.out.println("Daftar kamar yang dipesan: ");
        tampilkanDataReservasiKamar(0);
        System.out.println();
        hitungTotalBiayaReservasiSebelumDiskonDanBiayaTambahan(0);
        System.out.println("Total biaya reservasi sebelum pajak dan biaya layanan: " + formatRupiah(totalBiayaReservasiSebelumDiskonDanBiayaTambahan) + "\n");

        hitungTotalBiayaReservasiSetelahDiskonDanBiayaTambahan();
        kembaliKeMenuUtama();
    }


    // fungsi hitung total biaya reservasi sebelum diskon dan biaya tambahan
    private double hitungTotalBiayaReservasiSebelumDiskonDanBiayaTambahan(int indexDataReservasiKamar) {
        if (indexDataReservasiKamar < jumlahReservasiKamar) {
            totalBiayaReservasiSebelumDiskonDanBiayaTambahan += dataReservasiKamar[indexDataReservasiKamar].lamaMenginap * dataReservasiKamar[indexDataReservasiKamar].kamar.tipe.hargaPermalam;
            return hitungTotalBiayaReservasiSebelumDiskonDanBiayaTambahan(indexDataReservasiKamar + 1);
        }
        return totalBiayaReservasiSebelumDiskonDanBiayaTambahan;
    }

    // fungsi hitung total biaya reservasi setelah diskon dan biaya tambahan
    private void hitungTotalBiayaReservasiSetelahDiskonDanBiayaTambahan() {
        double biayaPajak = totalBiayaReservasiSebelumDiskonDanBiayaTambahan * 0.1;
        double biayaLayanan = 50_000 * jumlahReservasiKamar;
        double diskon = 0;
        if (totalBiayaReservasiSebelumDiskonDanBiayaTambahan > 500_000) {
            diskon = totalBiayaReservasiSebelumDiskonDanBiayaTambahan * 0.15;
        }

        String penawaranKhusus = "-";
        if (totalBiayaReservasiSebelumDiskonDanBiayaTambahan > 300_000) {
            penawaranKhusus = "Gratis sarapan";
        }

        System.out.println("Biaya pajak: " + formatRupiah(biayaPajak));
        System.out.println("Biaya layanan: " + formatRupiah(biayaLayanan));
        System.out.println("Total biaya reservasi sebelum pajak dan biaya layanan + pajak + biaya layanan: " + formatRupiah(totalBiayaReservasiSebelumDiskonDanBiayaTambahan + biayaPajak + biayaLayanan) + "\n");
        System.out.println("Diskon: " + formatRupiah(diskon));
        System.out.println("Penawaran khusus: " + penawaranKhusus);
        System.out.println("Total biaya reservasi setelah diskon: " + formatRupiah(totalBiayaReservasiSebelumDiskonDanBiayaTambahan + biayaPajak + biayaLayanan - diskon));

        System.out.println("\nKeterangan:");
        System.out.println("1. Biaya tambahan:");
        System.out.println("   * Pajak 10% dari total reservasi");
        System.out.println("   * Biaya layanan Rp. 50.000,- per kamar ");
        System.out.println("2. Diskon dan penawaran khusus:");
        System.out.println("   * Diskon 15% jika total biaya reservasi lebih dari Rp. 500.000,-");
        System.out.println("   * Gratis sarapan untuk semua tamu jika total biaya reservasi lebih dari Rp. 300.000,-");
    }

    private void keluar() {
        System.out.println("Anda telah keluar dari aplikasi, terima kasih...");
        System.exit(0);
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void kembaliKeMenuUtama() {
        String scanner = scanner("Kembali ke menu utama? [y/t]");
        if (scanner.equalsIgnoreCase("y")) {
            tampilkanMenu();
        } else if (scanner.equalsIgnoreCase("t")) {
            keluar();
        } else {
            System.out.println("Input tidak valid!, masukkan 'y' untuk ya atau 't' untuk tidak.");
            kembaliKeMenuUtama();
        }
    }

    private void tampilkanMenu() {
        clearConsole();
        System.out.println("-----------------------------------------");
        System.out.println("APLIKASI RESERVASI HOTEL");
        System.out.println("-----------------------------------------");
        System.out.println("Daftar Menu : ");
        System.out.println("1. Input Data Kamar");
        System.out.println("2. Pesan Kamar");
        System.out.println("3. Cetak Struk Reservasi");
        System.out.println("4. Keluar");
        System.out.println("-----------------------------------------");
        String pilihanMenu = scanner("Masukkan pilihan");
        switch (pilihanMenu) {
            case "1":
                tampilkanMenuInputDataKamar();
                break;
            case "2":
                tampilkanMenuPesanKamar();
                break;
            case "3":
                tampilkanMenuCetakStrukReservasi();
                break;
            case "4":
                keluar();
                break;
            default:
                System.out.println("Pilihan tidak tersedia!");
                kembaliKeMenuUtama();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.tampilkanMenu();
    }
}
