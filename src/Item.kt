package perpustakaan

/**
 * Kelas abstrak yang merepresentasikan item umum yang dapat dipinjam di perpustakaan.
 * Tidak bisa diinstansiasi langsung, gunakan subclass [Book], [Journal], atau [DVD].
 * @property id
 * @property title
 * @property year
*/

abstract class Item(
    val id: String,
    val title: String,
    val year: Int
){
    var isAvailable: Boolean = true
        private set

    abstract fun calculateFinePerDay(): Double
    abstract fun getItemType(): String
    abstract fun getMaxBorrowDays(): Int

    /**
     * @return
     */
    fun borrow(): Boolean{
        return if (isAvailable){
            isAvailable = false
            println("✅ '$title' berhasil dipinjam.")
            true
        } else {
          println("❌ '$title' sedang tidak tersedia")
            false
        }
    }

    /**
     * mengembalikan item, menghitung denda jika terlambat
     * @param daysLate
     * @return
     */

    open fun returnItem(daysLate: Int = 0): Double{
        if (isAvailable){
            println("⚠ '$title' tidak sedang dipinjam.")
            return 0.0
        }
        isAvailable = true
        val denda = daysLate * calculateFinePerDay()
        println("↩ '$title' dikembalikan.")
        if (denda > 0) println("    ⚠ Terlambat $daysLate hari, denda: Rp $denda")
        return denda
    }

    /** Menampilkan info item. Open agar subclass bisa menambah info spesifik. */
    open fun displayInfo(){
        println("ID          : $id")
        println("Judul       : $title")
        println("Tahun       : $year")
        println("Jenis       : ${getItemType()}")
        println("Status      : ${if (isAvailable) "Tersedia" else "Dipinjam"}")
        println("Denda/hari  : Rp ${calculateFinePerDay()}")
        println("Maks pinjam : ${getMaxBorrowDays()} hari")
    }
}