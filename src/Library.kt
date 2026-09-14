package perpustakaan

/**
 * Kelas utama pengelola perpustakaan
 * @property name Nama perpustakaan.
 */
class Library(val name: String){
    private val items: MutableList<Item> = mutableListOf()
    private val members: MutableList<Member> = mutableListOf()
    private val transactions: MutableList<Transaction> = mutableListOf()

    val totalItems: Int get() = items.size
    val availableItems: Int get() = items.count { it.isAvailable }
    val totalMembers: Int get() = members.size
    val totalTransactions: Int get() = transactions.size

    fun addItem(item: Item){
        items.add(item)
        println("✅ '${item.title}' ditambahkan ke $name.")
    }

    fun addItems(vararg newItems: Item){
        newItems.forEach { addItem(it) }
    }

    fun findItem(id: String): Item? = items.find { it.id == id }

    fun searchItems(keyword: String): List<Item> =
        items.filter{
            it.title.contains(keyword, ignoreCase = true)
            it.id.contains(keyword, ignoreCase = true)
        }

    fun registerMember(id: String, name: String, email: String, phone: String): Boolean{
        if (members.any {it.id == id}){
            println("❌ ID $id sudah terdaftar.")
            return false
        }
        members.add(Member(id, name, email, phone))
        println("✅ Anggota $name berhasil didaftarkan.")
        return true
    }

    fun findMember(id: String): Member? = members.find{ it.id == id }

    fun borrowItem(memberId: String, itemId: String): Transaction? {
        val member = findMember(memberId)
        val item = findItem(itemId)
        if (member == null || item == null){
            println("❌ Anggota atau item tidak ditemukan.")
            return null
        }
        val trx = member.borrowItem(item) ?: return null
        transactions.add(trx)
        return trx
    }

    fun returnItem(memberId: String, itemId: String, daysLate: Int = 0): Double{
        val member = findMember(memberId)
        val item = findItem(itemId)
        if (member == null || item == null){
            println("❌ Anggota atau item tidak ditemukan.")
            return 0.0
        }
        return member.returnItem(item, daysLate)
    }

    fun displayAllItems(){
        println("\n=== SEMUA ITEM ===")
        items.forEach { it.displayInfo(); println() }
        println("Total: $totalItems | Tersedia: $availableItems")
    }

    fun displayAvailableItems(){
        println("\n=== ITEM TERSEDIA ===")
        val tersedia = items. filter { it.isAvailable }
        if (tersedia.isEmpty()) println("(tidak ada)")
        else tersedia.forEach { println("[${it.id}] ${it.title} (${it.getItemType()})") }
    }

    fun displayAllMembers(){
        println("\n=== SEMUA ANGGOTA ===")
        members.forEach { it.displayInfo(); println() }
    }

    fun displayAllTransactions(){
        println("\n=== SEMUA TRANSAKSI ===")
        transactions.forEach { it.displayTransaction(); println() }
    }

    fun displayReport(){
        println("\n=== LAPORAN PERPUSTAKAAN ===")
        println("Nama               : $name")
        println("Total Item         : $totalItems")
        println("Tersedia           : $availableItems")
        println("Dipinjam           : ${totalItems - availableItems}")
        println("Total Anggota      : $totalMembers")
        println("Total Transaksi    : $totalTransactions")
        println("Total Denda        : Rp ${members.sumOf { it.totalFines }}")
    }
}