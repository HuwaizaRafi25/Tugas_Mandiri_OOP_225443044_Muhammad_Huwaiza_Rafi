package perpustakaan

/**
 * Merepresentasikan buku, mewarisi [Item]
 * @property author Penulis buku.
 * @property pages Jumlah halaman.
 * @property genre Genre Buku.
 */
class Book(
    id: String, title: String, year: Int,
    val author: String,
    val pages: Int,
    val genre: String
): Item(id, title, year){
    override fun calculateFinePerDay(): Double = 2000.0
    override fun getItemType(): String = "Buku"
    override fun getMaxBorrowDays(): Int = 14

    override fun displayInfo() {
        super.displayInfo()
        println("Penulis    : $author")
        println("Halaman    : $pages")
        println("Genre      : $genre")
    }
}