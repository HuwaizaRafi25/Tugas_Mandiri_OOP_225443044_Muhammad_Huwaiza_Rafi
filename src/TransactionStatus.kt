package perpustakaan

/**
 * Sealed class untuk status transaksi peminjaman.
 * Semua kemungkinan status didefinisikan dalam file ini agar `when` bisa ekshaustif.
 */
sealed class TransactionStatus{

    /** Representasi string status untuk ditampilkan. */
    abstract fun display(): String

    /** True jika status ini final (tidak bisa diubah lagi). */
    open fun isFinal(): Boolean = false

    /** Status: sedang dipinjam. */
    object Borrowed : TransactionStatus(){
        override fun display() = "📖 Dipinjam"
    }

    /** Status: sudah dikembalikan tepat waktu. */
    object Returned : TransactionStatus(){
        override fun display() = "✅ Dikembalikan"
        override fun isFinal() = true
    }

    /** Status: terlambat dikembalikan. @property daysLate jumlah hari terlambat. */
    data class Overdue(val daysLate: Int) : TransactionStatus(){
        override fun display() = "⚠️ Terlambat ($daysLate hari)"
        override fun isFinal() = true
    }

    /** Status: transaksi dibatalkan. */
    object Cancelled : TransactionStatus(){
        override fun display() = "❌ Dibatalkan"
        override fun isFinal() = true
    }
}