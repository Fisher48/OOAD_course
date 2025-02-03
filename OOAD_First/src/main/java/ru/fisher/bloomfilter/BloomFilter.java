package ru.fisher.bloomfilter;


abstract class BloomFilter<T> {

    // Интерфейс класса реализующий АТД BloomFilter

    // Конструктор - создает битовый массив длиной len
    public BloomFilter(int len) {}

    // Команды

    // Постусловие - успешно добавлена строка в фильтр
    public abstract void add(String str1);


    // Запросы:
    public abstract boolean isValue(String str1);  // проверка, имеется ли строка в фильтре

}

class BloomFilterImpl<T> extends BloomFilter<T> {

    private int filterLen;
    public int bitArr;

    public BloomFilterImpl(int len) {
        super(len);
        filterLen = len;
        bitArr = 0;
    }

    @Override
    public void add(String str1) {
    // добавляем строку str1 в фильтр
        int index1 = hash1(str1);
        int index2 = hash2(str1);
        bitArr |= (1 << index1);
        bitArr |= (1 << index2);
    }

    @Override
    public boolean isValue(String str1) {
        // проверка, имеется ли строка str1 в фильтре
        int index1 = hash1(str1);
        int index2 = hash2(str1);

        return (bitArr & (1 << index1)) != 0 && (bitArr & (1 << index2)) != 0;
    }

    // хэш-функции
    public int hash1(String str1) {
        int hash = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = str1.charAt(i);
            hash = (hash * 17 + code) % filterLen;
        }
        return Math.abs(hash);
    }

    public int hash2(String str1) {
        int hash = 0;
        for (int i = 0; i < str1.length(); i++) {
            int code = str1.charAt(i);
            hash = (hash * 223 + code) % filterLen;
        }
        return Math.abs(hash);
    }

}

