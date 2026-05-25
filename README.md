# Slovníkové testy - ALG2 TUL

Vzorové řešení zkouškové úlohy z předmětu **Algorithms and Computer Programming 2** na Technické univerzitě v Liberci (TUL).

## Zadání
Cílem aplikace je vytvořit nástroj pro generování a provádění slovníkových testů (např. překlad slovíček, hlavní města). Testy jsou načítány z externích textových souborů ve formátu `.stq`.

### Funkcionality
- Nastavení pracovního adresáře a automatická detekce testovacích souborů.
- Načítání a parsování souborů s pevně danou strukturou (otázky Q1, Q2 a položky testu).
- Podpora pro standardní i inverzní testování (překlad z Jazyka A do B i naopak).
- Generování náhodného "jednoduchého testu" (definovaný počet otázek) a "úplného testu" (všechny otázky).
- Zaznamenávání statistik a ukládání výsledků do textového logu (jméno, datum, čas, úspěšnost).

## Architektura programu
Program je navržen s důrazem na principy OOP a oddělení zodpovědností:
- **`com.tul.ui`**: Obsahuje třídu pro uživatelské rozhraní (konzolové menu), ošetřuje uživatelské vstupy a vypisuje výsledky.
- **`com.tul.wordle`**: Logická vrstva aplikace.
    - `Wordle`: Správce testů, řeší souborový systém a načítání dat.
    - `WordleTester`: Engine pro provádění konkrétní testovací relace (uchovává stav testu).
    - `DictionaryTest`, `WordPair`: Doménové objekty uchovávající data.

## Použité technologie
- Java SE (verze 25)
- Java NIO (Path, Paths, Files) pro práci se souborovým systémem.
- Java Time API pro práci s datem a časem.
- Kolekce (ArrayList, HashMap) a algoritmy pro zamíchání prvků (Collections.shuffle).
- I/O Streamy (BufferedReader, PrintWriter) s podporou kódování UTF-8.

## Jak spustit
1. Sestavte projekt pomocí Maven nebo v prostředí Apache NetBeans.
2. Připravte si složku s testovacími soubory `.stq`.
3. Spusťte hlavní třídu `UI` v balíčku `com.tul.ui`.
