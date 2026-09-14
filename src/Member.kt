package perpustakaan

/**
 * Merepresentasikan anggota perpustakaan
 * @property id ID anggota.
 * @property name Nama anggota.
 */
class Member(
    val id: String,
    val name: String,
    private val email: String,
    private val phone: String
){
    private val transactions: MutableList<Transaction> = mutableListOf()

    val transactionCount: Int get() = transactions.size

    val totalFines: Double
        get() = transactions
            .filter { it.status is TransactionStatus.Overdue }
            .sumOf { (it.status as TransactionStatus.Overdue).daysLate * it.item.calculateFinePerDay() }

    val activeBorrows: Int
        get() = transactions.count { it.status is TransactionStatus.Borrowed }

    fun getEmail(): String = email
    fun getPhone(): String = phone

    /**
     * Meminjam item. Maksimal 3 pinjaman aktif per anggota.
     * @return [Transaction] baru jika berhasil, null jika gagal.
     */
    fun borrowItem(item: Item): Transaction? {
        if (!item.isAvailable){
            println("❌ '${item.title}' tidak tersedia.")
            return null
        }
        if (activeBorrows >= 3){
            println("❌ $name sudah meminjam maksimal 3 item aktif.")
            return null
        }
        item.borrow()
        val trx = Transaction("TRX-${System.currentTimeMillis()}", item, this)
        transactions.add(trx)
        println("✅ $name berhasil meminjam '${item.title}'.")
        return trx
    }

    /**
     * Mengembalikan item yang sedang dipinjam anggota ini.
     * @return total denda, 0.0 jika transaksi tidak ditemukan.
     */
    fun returnItem(item: Item, daysLate: Int = 0): Double {
        val trx = transactions.find { it.item == item && it.status is TransactionStatus.Borrowed }
        if (trx == null){
            println("❌ Tidak ditemukan transaksi aktif untuk '${item.title}' pada $name.")
            return 0.0
        }
        return trx.returnItem(daysLate)
    }

    fun getTransactions(): List<Transaction> = transactions.toList()

    fun displayInfo(){
        println("ID             : $id")
        println("Nama           : $name")
        println("Email          : $email")
        println("Telepon        : $phone")
        println("Total Pinjam   : $transactionCount")
        println("Pinjam Aktif   : $activeBorrows")
        println("Total Denda    : Rp $totalFines")
    }

    fun displayTransactions(){
        println("\n--- Riwayat Transaksi: $name ---")
        if (transactions.isEmpty()) println("    (belum ada)")
        else transactions.forEach { it.displayTransaction() }
    }
}