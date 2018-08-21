package OpenAdressingHashTable;

/**
 * Реализация хеш-таблиц с открытой адресацией.
 * Открытая адрессация - метод построения хеш-таблиц,
 * при котором используется массив в качестве базовой структуры,
 * индексы - значения хеш-функций от ключа, значения массива - добаляемые элементы.
 * Пробная последовательность - последовательность, в которой просматриваются ячейки хеш-таблицы для вставки и поиска элемента.
 * Проще говоря, это последовательность элементов, которую необходимо пройти чтобы выполнить одно действие(пр. вставка, поиск).
 * Размер пробной последовательности говорит об общей плотности таблице(чем больше плотность, тем больше вероятность коллизии).
 * Для решения проблем коллизий используется метод линейного пробирования -
 * метод при котором в случае коллизий к текущему индексу в хеш-таблице прибавляем небольшое фиксированное число
 * (чаще всего 1, потому что при большом или четном значениии и числа и размера массива, существует вероятность постоянного проскакивания пустых ячеек).
 * Проблема данного метода заключается в том, что c ростом элементов образуются большие блоки смежных записей, следовательно
 * увеличивается пробная последовательность и скорость работы с хеш-таблицей уменьшается.
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

    /**
     * Добавляет запись ключ-значение в таблицу
     * ---------------------------
     * Сложность - O(1), при пробной последовательности 1-2,
     * O(N) - наихудший вариант, одно свободное мечто.
     * @param entry
     * @return
     */
    private boolean putInternal(Entry entry) {
        int tableIndex = hashFunction(entry.getKey());

        if (hashTable[tableIndex] == null) { // если коллизии нет, то вставляем элемент
            hashTable[tableIndex] = entry;
            return true;
        } else if (hashTable[tableIndex].getKey() != entry.getKey()) { // коллизия и заявленный ключ не дублируется
            int probingIndex = (tableIndex + probingConst) % tableSize;
            // проходим пробную последовательность, пока не сделаем круг(свободных мест нет)
            while (hashTable[tableIndex] != hashTable[probingIndex]) {
                if (hashTable[probingIndex] == null) { // нашли пустое место для вставки
                    hashTable[probingIndex] = entry;
                    return true;
                } else if (hashTable[probingIndex].getKey() == entry.getKey()){ // нашли дубликат ключа (нарушение условий хеш-тпблиц)
                    return false;
                }

                probingIndex = (probingIndex + probingConst) % tableSize;
            }

            // не нашли место для вставки, тогда расширяем массив,
            // и вставляем элемент, который несмогли вставить.
            expandTable();
            putInternal(entry);
        }

        return false;
    }

    /**
     * Рвсширение хеш таблицы вдвое.
     * Берем все элементы старого массива и рехешируем их ключи
     * и вставляем в увелеченный массив.
     * (Желательно проводить такую операцию при заполнении массива на 75%)
     */
    private void expandTable() {
        tableSize *= 2;
        Entry[] temp = hashTable;
        hashTable = new Entry[tableSize];

        for (int i = 0; i < temp.length; i++) {
            Entry current = temp[i];
            if (current != null) {
                putInternal(current);
            }
        }
    }

    public boolean put(int key, double value) {
        return putInternal(new Entry(key, value));
    }

    /**
     *  Возвращает значение по ключу
     * @param key
     * @return
     */
    public double get(int key) {
        int tableIndex = hashFunction(key);

        // нашли элемент, возвращаем рузультат
        if (hashTable[tableIndex].getKey() == key) {
            return hashTable[tableIndex].getValue();
        }

        // проходим пробную последовательность, пока она не закончится,
        // или не сделаем полный круг
        int probingIndex = (tableIndex + probingConst) % tableSize;
        while (hashTable[probingIndex] != null &&
                hashTable[probingIndex] != hashTable[tableIndex]) {
            if (hashTable[probingIndex].getKey() == key) { // нашли элемент, возвращаем рузультат
                return hashTable[probingIndex].getValue();
            }

            probingIndex = (probingIndex + probingConst) % tableSize;
        }

        throw new NullPointerException();
    }

    /**
     * Удаляет элемент из хеш-таблицы.
     * @param key
     * @return
     */
    public boolean remove(int key) {
        int tableIndex = hashFunction(key);

        if (hashTable[tableIndex] == null) { // значение результата хеш-функции от ключа не содержит значений
            return false;
        } else if (hashTable[tableIndex].getKey() == key) { // результат хеш-функции от ключа равен искомому
            hashTable[tableIndex] = null;
            return true;
        } else { // проходим по пробной последовательности в поисках элемента
            int probingIndex = (tableIndex + probingConst) % tableSize;
            // проходим пробную последовательность, пока она не закончится,
            // или не сделаем полный круг
            while (hashTable[probingIndex] != null &&
                    hashTable[probingIndex] != hashTable[tableIndex]) {
                if (hashTable[probingIndex].getKey() == key) { // нашли элемент, удаляем его
                    hashTable[probingIndex] = null;
                    return true;
                }

                probingIndex = (probingIndex + probingConst) % tableSize;
            }
        }

        // элемент не найден
        return false;
    }

    /**
     * Выводит хеш-таблицу в консоль по 10 элементов в строке.
     */
    public void display() {
        for (int i = 0; i < tableSize; i++) {
            System.out.print((hashTable[i] != null ? hashTable[i].getValue() : hashTable[i]) + ", ");
            if ((i + 1) % 11 == 0) System.out.println();
        }
    }
}
