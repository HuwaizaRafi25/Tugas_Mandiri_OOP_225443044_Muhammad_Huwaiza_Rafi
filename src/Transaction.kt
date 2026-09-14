package perpustakaan
import java.time.LocalDate

/**
 * Merepresentasikan satu transaksi peminjaman item oleh anggota
 * @property id ID unik transaksi.
 * @property item Item yang dipinjam.
 * @property member Anggota yang meminjam.
 * @property borrowDate Tanggal peminjaman.
 */
class Transaction(
    val id: String,
    val item: Item,
    val member: Member,
    val borrowDate: String = LocalDate.now().toString()
){
    var status: TransactionStatus = TransactionStatus.Borrowed

    /**
     * Memproses pengembalian item pada transaksi ini
     * @param daysLate jumlah hari keterlambatan
     * @return total denda, atau 0.0 jika transaksi sudah final
     */
    fun returnItem(daysLate: Int): Double{
        if (status.isFinal()){
            println("❌ Transaksi #$id sudah final, tidak bisa diproses lagi.")
        }
        val denda = item.returnItem(daysLate)
        status = if (daysLate > 0) TransactionStatus.Overdue(daysLate)
                    else TransactionStatus.Returned
        return denda
    }

    /** Membatalkan transaksi ini, mengembalikan item ke status tersedia. */
    fun cancel(){
        if (status.isFinal()){
            println("❌ Transaksi #$id sudah final, tidak bisa dibatalkan.")
            return
        }
        status = TransactionStatus.Cancelled
        item.returnItem(0)
    }

    /** Menampilkan informasi lengkap transaksi */
    fun displayTransaction(){
        println("Transaksi #$id")
        println("   Item      : ${item.title} (${item.getItemType()})")
        println("   Peminjam  : ${member.name}")
        println("   Tanggal   : $borrowDate")
        println("   Status    : ${status.display()}")
    }
}