# Tugas Mandiri OOP - Sistem Manajemen Perpustakaan Digital

## Identitas
- **Nama**: Muhammad Huwaiza Rafi
- **NIM**: 225443044
- **Program Studi**: D4 Teknologi Rekayasa Informatika Industri
- **Dosen**: M Harry K Saputra

## Deskripsi Singkat
Program ini adalah simulasi sistem manajemen perpustakaan digital berbasis Kotlin yang menerapkan 4 pilar OOP (Enkapsulasi, Pewarisan, Polimorfisme, Abstraksi). Sistem dapat mengelola tiga jenis item (Buku, Jurnal, DVD), anggota perpustakaan, serta transaksi peminjaman dan pengembalian lengkap dengan perhitungan denda keterlambatan.

## Cara Menjalankan Program
1. Buka project ini di IntelliJ IDEA.
2. Pastikan JDK 17+ sudah terpasang.
3. Buka file `Main.kt`.
4. Klik ikon ▶️ run di sebelah `fun main()`, atau tekan `Shift + F10`.
5. Output akan muncul di jendela Run/Console.

## Struktur Kelas
| Kelas | Tipe | Deskripsi |
|---|---|---|
| `Item` | Abstract class | Kelas induk untuk semua item perpustakaan |
| `Book` | Class (mewarisi Item) | Merepresentasikan buku |
| `Journal` | Class (mewarisi Item) | Merepresentasikan jurnal |
| `DVD` | Class (mewarisi Item) | Merepresentasikan DVD |
| `TransactionStatus` | Sealed class | Status transaksi (Borrowed, Returned, Overdue, Cancelled) |
| `Transaction` | Class | Menghubungkan item, anggota, dan status peminjaman |
| `Member` | Class | Merepresentasikan anggota perpustakaan |
| `Library` | Class | Kelas utama pengelola seluruh operasi perpustakaan |

## Fitur Utama
- Manajemen item (tambah, cari)
- Registrasi & pencarian anggota
- Peminjaman dengan batas maksimal 3 item aktif per anggota
- Pengembalian dengan perhitungan denda otomatis berdasarkan jenis item
- Laporan ringkasan perpustakaan