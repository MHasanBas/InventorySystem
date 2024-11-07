import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InventorySystem {
    List<Item> inventaris;
    boolean mainProcess;
    int pilihanMenu;
    Scanner sc;
    private int batasMinimumStok = 10;

    public InventorySystem() {
        inventaris = new ArrayList<>();
        mainProcess = false;
        pilihanMenu = 0;
        sc = new Scanner(System.in);
    }

    public void halamanLogin() {
        System.out.println("========== Sistem Inventaris Restoran ==========");
        System.out.println("Silakan masuk dengan akun Anda:");

        boolean loginSukses = false;

        while (!loginSukses) {
            System.out.print("Username: ");
            String username = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();

            if (username.equals("b") && password.equals("b")) {
                loginSukses = true;
                mainProcess = true;
            } else {
                System.out.println("Masih Salah Rekk!!! . Silakan coba lagi :) ");
            }
        }
    }

    public void mainProcess() {
        halamanLogin();
    }

    public void menu() {
        System.out.println("\n========== Menu Utama ==========");
        System.out.println("1. Tampilkan Inventaris");
        System.out.println("2. Tambah Barang");
        System.out.println("3. Perbarui Stok");
        System.out.println("4. Gunakan Barang");
        System.out.println("5. Cari Barang");
        System.out.println("6. Generate Laporan");
        System.out.println("7. Hapus Barang");
        System.out.println("8. stok barang yang habis");
        System.out.println("9. Keluar");
        System.out.print("Masukkan pilihan Anda: ");
        pilihanMenu = sc.nextInt();
        sc.nextLine();
    }

    public void showInventory() {
        System.out.println("\n========== Inventaris ==========");
        if (inventaris.isEmpty()) {
            System.out.println("Tidak ada barang dalam inventaris.");
        } else {
            for (Item barang : inventaris) {
                System.out.println("Kode Barang: " + barang.getKodeBarang());
                System.out.println("Nama Barang: " + barang.getNamaBarang());
                System.out.println("Kategori Barang: " + barang.getKategoriBarang());
                System.out.println("Stok Baik: " + barang.getStokBaik());
                System.out.println("Stok Busuk/Expired: " + barang.getStokRusak());
                System.out.println("Jumlah Digunakan: " + barang.getJumlahDigunakan());
                System.out.println("---------------------------");
            }
        }
    }

    public void addItem() {
        System.out.println("\n========== Tambah Barang ==========");
        System.out.print("Masukkan Kode Barang: ");
        String kodeBarang = sc.nextLine();
        System.out.print("Masukkan Nama Barang: ");
        String namaBarang = sc.nextLine();
        System.out.print("Kategori Barang (1: Stok Segar, 2: Stok Busuk/Expired): ");
        String kategoriBarang = sc.nextLine();
        System.out.print("Masukkan Jumlah Barang: ");
        int jumlahBarang = sc.nextInt();
        sc.nextLine();

        Item item = new Item(kodeBarang, namaBarang, kategoriBarang, jumlahBarang);
        inventaris.add(item);

        if (kategoriBarang.equalsIgnoreCase("1")) {
            item.setStokBaik(jumlahBarang);
        } else if (kategoriBarang.equalsIgnoreCase("2")) {
            item.setStokRusak(jumlahBarang);
        }

        System.out.println("Barang berhasil ditambahkan.");
    }

    public void updateStock() {
        System.out.println("\n========== Perbarui Stok ==========");
        System.out.print("Masukkan Kode Barang: ");
        String kodeBarang = sc.nextLine();
        System.out.print("Masukkan Stok segar: ");
        int stokBaik = sc.nextInt();
        sc.nextLine();
        System.out.print("Masukkan Stok busuk(expired): ");
        int stokRusak = sc.nextInt();
        sc.nextLine();

        Item barang = cariBarangBerdasarkanKode(kodeBarang);
        if (barang != null) {
            barang.setStokBaik(stokBaik);
            barang.setStokRusak(stokRusak);
            System.out.println("Stok berhasil diperbarui.");
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    public void useItem() {
        System.out.println("\n========== Gunakan Barang ==========");
        System.out.print("Masukkan Kode Barang: ");
        String kodeBarang = sc.nextLine();

        Item barang = cariBarangBerdasarkanKode(kodeBarang);
        if (barang != null) {
            System.out.print("Pilih aksi (1: Pakai / 2: Buang): ");
            int aksi = sc.nextInt();
            sc.nextLine();

            if (aksi == 1) {
                System.out.print("Masukkan Jumlah Digunakan: ");
                int jumlahDigunakan = sc.nextInt();
                sc.nextLine();

                if (jumlahDigunakan <= barang.getStokBaik()) {
                    barang.setStokBaik(barang.getStokBaik() - jumlahDigunakan);
                    barang.setJumlahDigunakan(barang.getJumlahDigunakan() + jumlahDigunakan);
                    System.out.println("Penggunaan barang segar berhasil.");
                } else {
                    System.out.println("Stok barang segar tidak mencukupi.");
                }
            } else if (aksi == 2) {
                System.out.print("Masukkan Jumlah yang Dibuang: ");
                int jumlahDibuang = sc.nextInt();
                sc.nextLine();

                if (jumlahDibuang <= barang.getStokRusak()) {
                    barang.setStokRusak(barang.getStokRusak() - jumlahDibuang);
                    System.out.println("Pembuangan barang busuk berhasil.");
                } else {
                    System.out.println("Stok barang busuk tidak mencukupi.");
                }
            } else {
                System.out.println("Pilihan aksi tidak valid.");
            }
        } else {
            System.out.println("Barang tidak ditemukan.");
        }
    }

    public void searchItem() {
        System.out.println("\n========== Cari Barang ==========");
        System.out.print("Masukkan kata kunci: ");
        String kataKunci = sc.nextLine();

        List<Item> hasilPencarian = new ArrayList<>();
        for (Item barang : inventaris) {
            if (barang.getKodeBarang().contains(kataKunci) ||
                    barang.getNamaBarang().contains(kataKunci) ||
                    barang.getKategoriBarang().contains(kataKunci)) {
                hasilPencarian.add(barang);
            }
        }

        if (hasilPencarian.isEmpty()) {
            System.out.println("Tidak ada barang yang ditemukan sesuai kriteria pencarian.");
        } else {
            System.out.println("Hasil Pencarian:");
            for (Item barang : hasilPencarian) {
                System.out.println(barang);
                System.out.println("---------------------------");
            }
        }
    }

    public void generateReport() {
        System.out.println("\n========== Generate Laporan ==========");
        System.out.println("Total Barang: " + inventaris.size());

        int totalStokBaik = 0;
        int totalStokRusak = 0;
        int totalJumlahDigunakan = 0;
        for (Item barang : inventaris) {
            totalStokBaik += barang.getStokBaik();
            totalStokRusak += barang.getStokRusak();
            totalJumlahDigunakan += barang.getJumlahDigunakan();
        }

        System.out.println("Total Stok segar: " + totalStokBaik);
        System.out.println("Total Stok busuk(expired): " + totalStokRusak);
        System.out.println("Total Jumlah Digunakan: " + totalJumlahDigunakan);
    }

    public void displayItemsToRestock() {
        System.out.println("\n========== Barang yang Harus Diisi Stoknya ==========");
        boolean foundItems = false;

        for (Item barang : inventaris) {
            if (barang.getKategoriBarang().equalsIgnoreCase("1") && barang.getStokBaik() <= batasMinimumStok) {
                System.out.println(barang.getNamaBarang());
                foundItems = true;
            }
        }

        if (!foundItems) {
            System.out.println("Tidak ada barang yang perlu diisi stoknya.");
        }
    }

    public void deleteItem() {
        System.out.println("\n========== Hapus Barang ==========");
        System.out.println("1. Hapus Barang berdasarkan ID");
        System.out.println("2. Hapus Barang berdasarkan Nama");
        System.out.print("Masukkan pilihan Anda: ");
        int pilihanHapus = sc.nextInt();
        sc.nextLine();

        if (pilihanHapus == 1) {
            System.out.print("Masukkan ID Barang yang akan dihapus: ");
            String idBarang = sc.nextLine();
            boolean barangDitemukan = false;

            for (Item barang : inventaris) {
                if (barang.getKodeBarang().equals(idBarang)) {
                    inventaris.remove(barang);
                    barangDitemukan = true;
                    break;
                }
            }

            if (barangDitemukan) {
                System.out.println("Barang dengan ID " + idBarang + " berhasil dihapus.");
            } else {
                System.out.println("Barang dengan ID " + idBarang + " tidak ditemukan.");
            }
        } else if (pilihanHapus == 2) {
            System.out.print("Masukkan Nama Barang yang akan dihapus: ");
            String namaBarang = sc.nextLine();
            boolean barangDitemukan = false;

            for (Item barang : inventaris) {
                if (barang.getNamaBarang().equalsIgnoreCase(namaBarang)) {
                    inventaris.remove(barang);
                    barangDitemukan = true;
                    break;
                }
            }

            if (barangDitemukan) {
                System.out.println("Barang dengan Nama " + namaBarang + " berhasil dihapus.");
            } else {
                System.out.println("Barang dengan Nama " + namaBarang + " tidak ditemukan.");
            }
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    public boolean isMainProcess() {
        return mainProcess;
    }

    public int getMenuOption() {
        return pilihanMenu;
    }

    private Item cariBarangBerdasarkanKode(String kodeBarang) {
        for (Item barang : inventaris) {
            if (barang.getKodeBarang().equals(kodeBarang)) {
                return barang;
            }
        }
        return null;
    }
}