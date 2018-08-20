package DirectLinkingHashTable;

/**
 * Реализация хеш-таблицы с прямым связыванием (Метод цепочек)(способ решения коллизий).
 * Массив(базовая структура) содержит связанные списки значений с одинаковым результатом хеш-функции.
 */
public class DirectLinkingHashTable {
    private Node[] hashTable;
    private int blockCount = 100;

    public DirectLinkingHashTable() {
        hashTable = new Node[blockCount];
    }

    /**
     * Пример реализации хеш функции.
     * Обычно для размещения элементов используют специальные ключи
     * @param key
     * @return
     */
    private int hashFunction(int key) {
        return (key % blockCount);
    }

    /**
     * Добавлеям новый элемент с заданным ключом.
     * По определению, хеш-таблица должна содержать только уникальные ключи
     * поэтому идет проверка на использование данного ключа.
     * -------------------------
     * Сложность - O(N/B), где N - кол-во элементов,
     * B - кол-во блоков.
     * @param key
     * @param value
     * @return
     */
    public boolean put(int key, double value) {
        Node node = new Node(key, value);
        int tableIndex = hashFunction(key);

        Node current = hashTable[tableIndex];
        while (current != null) {
            if (current.getKey() == key) return false;
            current = current.getNext();
        }

        node.setNext(hashTable[tableIndex]);
        hashTable[tableIndex] = node;
        return true;
    }

    /**
     * Возвращает элемент по ключу.
     * Если ключа нет, то кидаем исключение.
     * Суть - находим по хеш-функции блок,
     * и ищем в нем элемент с указанным ключом
     * Сложность - O(N/B),
     * где N - количество элементов,
     * B - количество блоков.
     * @param key
     * @return
     */
    public double get(int key) {
        int tableIndex = hashFunction(key);

        Node current = hashTable[tableIndex];
        while (current != null) {
            if (current.getKey() == key) {
                return  current.getValue();
            }
            current = current.getNext();
        }
        throw new NullPointerException();
    }

    /**
     * Удаляет элемент с ключем key.
     * Если удаление прошо успешно, возвращаем true,
     * если элемента с заданным ключом нет, то возвращаем false.
     * @param key
     * @return
     */
    public boolean remove(int key) {
        int tableIndex = hashFunction(key);

        // Удаляем элемент из цепочки таблицы с индексом tableIndex
        Node prev = null;
        Node current = hashTable[tableIndex];
        while (current != null) {
            if (current.getKey() == key) {
                if (prev == null) {
                    hashTable[tableIndex] = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                current.setNext(null);
                return true;
            }
            prev = current;
            current = current.getNext();
        }

        return false;
    }

    private void printLine(int tableIndex) {
        Node current = hashTable[tableIndex];
        while (current != null) {
            System.out.println(current.getValue());
            current = current.getNext();
        }
    }
}
