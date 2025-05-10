/**
 * Project : Aplikasi Reservasi Hotel
 * Author: Dede Irwanto
 * Email: id.dedeirwanto@yahoo.com
 * Telegram : @dedeirwanto
 * Date: 02/05/2025
 * Time: 12:48 PM
 */
public class TipeKamar {
    tipeKamar nama;
    double hargaPermalam;

    enum tipeKamar {
        STANDAR,
        SUPERIOR,
        DELUXE,
        SUITE
    }

    public TipeKamar tipeStandar() {
        TipeKamar tipeKamar = new TipeKamar();
        tipeKamar.nama = TipeKamar.tipeKamar.STANDAR;
        tipeKamar.hargaPermalam = 150_000;
        return tipeKamar;
    }

    public TipeKamar tipeSuperior() {
        TipeKamar tipeKamar = new TipeKamar();
        tipeKamar.nama = TipeKamar.tipeKamar.SUPERIOR;
        tipeKamar.hargaPermalam = 200_000;
        return tipeKamar;
    }

    public TipeKamar tipeDeluxe() {
        TipeKamar tipeKamar = new TipeKamar();
        tipeKamar.nama = TipeKamar.tipeKamar.DELUXE;
        tipeKamar.hargaPermalam = 250_000;
        return tipeKamar;
    }

    public TipeKamar tipeSuite() {
        TipeKamar tipeKamar = new TipeKamar();
        tipeKamar.nama = TipeKamar.tipeKamar.SUITE;
        tipeKamar.hargaPermalam = 300_000;
        return tipeKamar;
    }
}
