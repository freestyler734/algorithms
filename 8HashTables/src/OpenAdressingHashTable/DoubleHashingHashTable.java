package OpenAdressingHashTable;

/**
 * Реализация хеш-таблиц с открытой адресацией.
 * Открытая адрессация - метод построения хеш-таблиц,
 * при котором используется массив в качестве базовой структуры,
 * индексы - значения хеш-функций от ключа, значения массива - добаляемые элементы.
 * Пробная последовательность - последовательность, в которой просматриваются ячейки хеш-таблицы для вставки и поиска элемента.
 * Проще говоря, это последовательность элементов, которую необходимо пройти чтобы выполнить одно действие(пр. вставка, поиск).
 * Размер пробной последовательности говорит об общей плотности таблице(чем больше плотность, тем больше вероятность коллизии).
 * Для решения проблем коллизий используется метод двойного хеширования -
 * суть используем 2 хеш функции и строим пробную последовательность как сумму текущего индекса +
 * результат второй хеш-функции от ключа домноженное на N (K, K + 1 * hash2(key),  + 2 * hash2(key) и т.д.)
 * Данный метод прибирования решает проблему первичной кластеризации (при которой пробная последовательность увеличивается и блоки записей сливаются).
 * Но не способна решить вторичную(элементы с одинаковм начальным индексам получают одну и ту же пробную последовательнось, иногда очень длинную).
 * Также из-за больших прибаляемых значений, алгоритм иногда постоянно перепрыгивает пустые ячейки.
 * Данный метод помогает решить проблемы первичной и вторичной кластаризации,
 * т.к. если одна хеш-функция дает одно значение для разных ключей, то не факт, что вторая функция даст одно значеие.
 */
public class DoubleHashingHashTable {
    private int tableSize = 10;
    private int probingConst = 1;
    private Entry[] hashTable;

    public DoubleHashingHashTable() {
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
     * Испольуется для построения пробной последовательности.
     * @param key
     * @return
     */
    private int hashFunction2(int key) {
        return (key % 11);
    }

    /**
     * Добавляет запись ключ-значение в таблицу
     * ---------------------------
     * Сложность - O(1), но увеличивается при увеличении плотности таблицы
     * @param entry
     * @return
     */
    private boolean putInternal(Entry entry) {
        int tableIndex = hashFunction(entry.getKey());


        if (hashTable[tableIndex] == null) { // если коллизии нет, то вставляем элемент
            hashTable[tableIndex] = entry;
            return true;
        } else if (hashTable[tableIndex].getKey() != entry.getKey()) { // коллизия и заявленный ключ не дублируется
            int currentProbingConst = probingConst;
            int probingIndex = (tableIndex + (currentProbingConst * hashFunction2(entry.getKey()))) % tableSize;
            // проходим пробную последовательность, пока не сделаем круг
            // (не вернемся к начальному значению - последовательность повторяется)
            while (hashTable[tableIndex] != hashTable[probingIndex]) {
                if (hashTable[probingIndex] == null) { // нашли пустое место для вставки
                    hashTable[probingIndex] = entry;
                    return true;
                } else if (hashTable[probingIndex].getKey() == entry.getKey()){ // нашли дубликат ключа (нарушение условий хеш-тпблиц)
                    return false;
                }

                probingIndex = (tableIndex + (++currentProbingConst * hashFunction2(entry.getKey()))) % tableSize;
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
        int currentProbingConst = probingConst;
        int probingIndex = (tableIndex + (currentProbingConst * hashFunction2(key))) % tableSize;
        while (hashTable[probingIndex] != null &&
                hashTable[probingIndex] != hashTable[tableIndex]) {
            if (hashTable[probingIndex].getKey() == key) { // нашли элемент, возвращаем рузультат
                return hashTable[probingIndex].getValue();
            }

            probingIndex = (tableIndex + (++currentProbingConst * hashFunction2(key))) % tableSize;
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
            int currentProbingConst = probingConst;
            int probingIndex = (tableIndex + (currentProbingConst * hashFunction2(key))) % tableSize;
            // проходим пробную последовательность, пока она не закончится,
            // или не сделаем полный круг
            while (hashTable[probingIndex] != null &&
                    hashTable[probingIndex] != hashTable[tableIndex]) {
                if (hashTable[probingIndex].getKey() == key) { // нашли элемент, удаляем его
                    hashTable[probingIndex] = null;
                    return true;
                }

                probingIndex = (tableIndex + (++currentProbingConst * hashFunction2(key))) % tableSize;
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
