

class Item {
    String kodeBarang;
    String namaBarang;
    String kategoriBarang;
    int stokBaik;
    int stokRusak;
    int jumlahDigunakan;

    public Item(String kodeBarang, String namaBarang, String kategoriBarang, int jumlahBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategoriBarang = kategoriBarang;
        stokBaik = 0;
        stokRusak = 0;
        jumlahDigunakan = 0;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKategoriBarang() {
        return kategoriBarang;
    }

    public int getStokBaik() {
        return stokBaik;
    }

    public void setStokBaik(int stokBaik) {
        this.stokBaik = stokBaik;
    }

    public int getStokRusak() {
        return stokRusak;
    }

    public void setStokRusak(int stokRusak) {
        this.stokRusak = stokRusak;
    }

    public int getJumlahDigunakan() {
        return jumlahDigunakan;
    }

    public void setJumlahDigunakan(int jumlahDigunakan) {
        this.jumlahDigunakan = jumlahDigunakan;
    }

    public String toString() {
        return "Kode Barang: " + kodeBarang + "\n"
                + "Nama Barang: " + namaBarang + "\n"
                + "Kategori Barang: " + kategoriBarang + "\n"
                + "Stok Baik: " + stokBaik + "\n"
                + "Stok Rusak: " + stokRusak + "\n"
                + "Jumlah Digunakan: " + jumlahDigunakan;
    }
}