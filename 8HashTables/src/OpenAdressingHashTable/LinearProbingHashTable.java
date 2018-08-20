package OpenAdressingHashTable;

/**
 * Реализация хеш-таблиц с открытой адресацией.
 * Открытая адрессация - метод построения хеш-таблиц,
 * при котором используется массив в качестве базовой структуры,
 * индексы - значения хеш-функций от ключа, значения массива - элементы.
 * Пробная последовательность - последовательность, в которой просматриваются ячейки хеш-таблицы для вставки и поиска элемента.
 * Проще говоря, это последовательность элементов, которую необходимо пройти чтобы выполнить действие(вставка, поиск).
 * Размер пробной последовательности говорит об общей плотности таблице(чем больше плотность, тем больше вероятность коллизии).
 * Для решения проблем коллизий используется метод линейного пробирования -
 * метод при котором в случае коллизий к текущему индексу в хеш-таблице прибавляем небольшое фиксированное число
 * (чаще всего 1, потому что при большом или четном значениии и числа и размера массива, существует вероятность постоянного проскакивания пустых ячеек).
 * Проблема данного метода заключается в том, что образуются большие блоки смежных записей, следовательно
 * увелисивается пробная последовательность и скорость работы с хеш-таблицей уменьшается.
 */
public class LinearProbingHashTable {
    private int tableSize = 10;
    private int probingConst = 1;
    private Entry[] hashTable;

    public LinearProbingHashTable() {
        hashTable = new Entry[tableSize];
    }

    /**
     * Пример реализации хеш функции.
     * Обычно для размещения элементов используют специальные ключи
     * @param key
     * @return
     */
    private int hashFunction(int key) {
        return (key % tableSize);
    }

    public boolean put(int key, double value) {
        int tableIndex = hashFunction(key);

        if (hashTable[tableIndex] == null) {
            hashTable[tableIndex] = new Entry(key, value);
            return true;
        } else if (hashTable[tableIndex].getKey() != key) { // коллизия и ключи не совпадают
            int probingIndex = (tableIndex + probingConst) % tableSize;
            // проходим пробную последовательность, пока не сделаем круг(свободных мест нет).
            while (hashTable[tableIndex] != hashTable[probingIndex]) {
                if (hashTable[probingIndex] == null) {
                    hashTable[probingIndex] = new Entry(key, value);
                    return true;
                } else if (hashTable[probingIndex].getKey() == key){
                    return false;
                }

                probingIndex = (probingIndex + probingConst) % tableSize;
            }
        }

        return false;
    }

    public void display() {
        for (int i = 0; i < tableSize; i++) {
            System.out.print((hashTable[i] != null ? hashTable[i].getValue() : hashTable[i]) + ", ");
            if ((i + 1) % 11 == 0) System.out.println();
        }
    }
}
