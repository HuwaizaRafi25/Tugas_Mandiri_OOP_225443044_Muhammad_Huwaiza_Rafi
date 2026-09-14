package perpustakaan

fun main(){
    // 1. Inisialisasi
    val library = Library("Perpustakaan Kampus")

    // 2. Tambah item
    library.addItems(
        Book("B001", "Pemrograman Kotlin", 2023, "Budi Santoso", 350, "Programming"),
        Book("B002", "Dasar-Dasar OOP", 2022, "Siti Rahayu", 280, "Education"),
        Journal("J001", "Jurnal Teknologi Informasi", 2023, "ITB", 15, 2),
        Journal("J002", "Jurnal Pendidikan", 2022, "UGM", 10, 1),
        DVD("D001", "Inception", 2010, "Christopher Nolan", 148, "Sci-Fi"),
        DVD("D002", "The Wind Rises", 2013, "Hayao Miyazaki", 126, "Biografi")
    )

    // 3. Registrasi anggota
    library.registerMember("M001", "Ahmad Fauzi", "ahmad@gmail.com", "08123456789")
    library.registerMember("M002", "Dewi Lestari", "dewi@gmail.com", "08098765432")
    library.registerMember("M003", "Ringgo Dzulkifli", "jul@gmail.com", "08877766643")

    // 4. Tampilkan semua item
    library.displayAllItems()

    // 5. Peminjaman (Skenario A)
    println("\n=== PEMINJAMAN ===")
    library.borrowItem("M001", "B001")
    library.borrowItem("M001", "D001")
    library.borrowItem("M002", "J001")
    library.borrowItem("M003", "B002")

    // 6. Item tersedia
    library.displayAvailableItems()

    // 7. Transaksi anggota
    library.findMember("M001")?.displayTransactions()
    library.findMember("M002")?.displayTransactions()

    // 8. Pengembalian (Skenario B)
    println("\n=== PENGEMBALIAN ===")
    library.returnItem("M001", "B001", 0)
    library.returnItem("M002", "J001", 3)

    // 9. Transaksi setelah pengembalian
    library.findMember("M001")?.displayTransactions()
    library.findMember("M002")?.displayTransactions()

    // 10. Demonstrasi Polimorfisme
    println("\n=== POLIMORFISME ===")
    val contohItem: List<Item> = listOf(
        Book("B999", "Contoh Buku", 2024, "X", 100, "Y"),
        Journal("J999", "Contoh Jurnal", 2024, "Z", 1, 1),
        DVD("D999", "Contoh DVD", 2024, "W", 90, "V")
    )
    contohItem.forEach {
        println("${it.getItemType()} - Denda/hari: Rp ${it.calculateFinePerDay()}")
    }

    // 11. Demonstrasi Sealed Class
    println("\n=== SEALED CLASS ===")
    val statusList: List<TransactionStatus> = listOf(
        TransactionStatus.Borrowed,
        TransactionStatus.Returned,
        TransactionStatus.Overdue(5),
        TransactionStatus.Cancelled
    )
    statusList.forEach { status ->
        val hasil = when (status){
            is TransactionStatus.Borrowed -> status.display()
            is TransactionStatus.Returned -> status.display()
            is TransactionStatus.Overdue -> status.display()
            is TransactionStatus.Cancelled -> status.display()
        }
        println(hasil)
    }

    // 12. Demonstrasi Smart Casting
    println("\n=== SMART CASTING ===")
    val b001 = library.findItem("B001")
    if (b001 != null){
        when (b001){
            is Book -> println("Item B001 adalah Buku, penulis: ${b001.author}")
            is Journal -> println("Item B001 adalah Jurnal")
            is DVD -> println("Item B001 adalah DVD")
        }
        val sebagaiDVD = b001 as? DVD
        println("Coba cast B001 ke DVD: ${sebagaiDVD ?: "Gagal, hasil null, karena B001 sebenarnya Buku"}")
    }

    // 13. Demonstrasi Enkapsulasi
    println("\n=== ENKAPSULASI ===")
    // b001?.isAvailable = true         // ❌ ERROR: 'isAvailable' punya private set
    // val emailAhmad = ahmadMember.email         // ❌ ERROR: 'email' bersifat private
    println("isAvailable pakai 'private set' -> hanya bisa diubah lewat borrow()/returnItem().")
    println("email & phone pakai 'private val' -> hanya bisa diakses lewat getEmail()/getPhone().")
    println("Ini melindungi data dari perubahan sembarangan dari luar kelas (enkapsulasi).")

    // 14. Laporan akhir
    library.displayReport()
}