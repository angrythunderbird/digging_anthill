class StartField(private val length: Int, private val depth: Int) {
    private val list = mutableListOf<MutableList<String>>()

    fun buildMatrix(): MutableList<MutableList<String>> {
        for (i in 0..<depth) {
            val row: MutableList<String> = mutableListOf()

            for (j in 0..<length) {
                row.add(" * ")
            }

            list.add(row)
        }
        return list
    }
}