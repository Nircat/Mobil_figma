package com.example.mobil_figma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScheduleData {
    // колонки: Время | Пн | Вт | Ср | Чт | Сб
    public static final int NUM_COLS = 6;

    private static List<Cell> header() {
        return Arrays.asList(
                new Cell("Время", CellType.HEADER),
                new Cell("Понедельник", CellType.HEADER),
                new Cell("Вторник", CellType.HEADER),
                new Cell("Среда", CellType.HEADER),
                new Cell("Четверг", CellType.HEADER),
                new Cell("Суббота", CellType.HEADER)
        );
    }

    public static List<List<Cell>> table1() {
        List<List<String>> rows = Arrays.asList(
                row("09:00–10:00",
                        "Пилатес\n(Зал 1, Мария)",
                        "Функц. тренинг\nИгорь",
                        "Бокс\n(Зал 1, Мария)",
                        "Пилатес\n(Зал 1, Мария)",
                        "Пилатес\n(Зал 1, Анна)"),
                row("17:00–18:00",
                        "Кроссфит\n(Зал 1, Андрей)",
                        "TRX\n(Зал 1, Елена)",
                        "Бокс\n(Зал 1, Андрей)",
                        "Бокс\n(Зал 1, Анна)",
                        "—"),
                row("19:00–20:00",
                        "Зумба\n(Зал 1, Екатерина)",
                        "Зумба\n(Зал 1, Андрей)",
                        "Бокс\n(Зал 1, Андрей)",
                        "Йога\n(Зал 1, Анна)",
                        "Йога\nАнна"),
                row("19:00–20:00",
                        "Зумба\n(Зал 1, Андрей)",
                        "Зумба\n(Зал 1, Андрей)",
                        "Кросс-фит\n(Игорь)",
                        "Йога\nАнна",
                        "Йога\nАнна")
        );
        return buildMatrix(rows);
    }

    public static List<List<Cell>> table2() {
        List<List<String>> rows = Arrays.asList(
                row("10:00–11:00",
                        "Йога\n(Зал 1, Анна)",
                        "Силовая\n(Зал 2, Иван)",
                        "Йога\n(Зал 1, Анна)",
                        "Йога\n(Зал 1, Анна)",
                        "Пилатес\n(Зал 1, Анна)"),
                row("18:00–19:00",
                        "Кроссфит\n(Зал 2, Алексей)",
                        "Кросс-фит\n(Зал 2, Алексей)",
                        "Бокс\n(Зал 1, Сергей)",
                        "Бокс\n(Зал 1, Сергей)",
                        "—"),
                row("19:30–20:30",
                        "Стретчинг\n(Зал 1, Ольга)",
                        "Табата\n(Зал 2, Дмитрий)",
                        "Табата\n(Зал 1, Дмитрий)",
                        "Бокс\n(Зал 2, Сергей)",
                        "Пилатес\n(Зал 1, Мария)"),
                row("19:30–20:30",
                        "Стретчинг\n(Зал 1, Ольга)",
                        "Табата\n(Зал 2, Дмитрий)",
                        "Табата\n(Зал 2, Дмитрий)",
                        "Бокс\n(Зал 2, Сергей)",
                        "Пилатес\n(Зал 1, Мария)")
        );
        return buildMatrix(rows);
    }

    // helpers
    private static List<String> row(String time, String mon, String tue, String wed, String thu, String sat) {
        return Arrays.asList(time, mon, tue, wed, thu, sat);
    }

    private static List<List<Cell>> buildMatrix(List<List<String>> rows) {
        List<List<Cell>> matrix = new ArrayList<>();
        matrix.add(header());
        for (List<String> r : rows) {
            List<Cell> line = new ArrayList<>();
            line.add(new Cell(r.get(0), CellType.TIME));
            for (int i = 1; i < r.size(); i++) {
                line.add(new Cell(r.get(i), CellType.DATA));
            }
            matrix.add(line);
        }
        return matrix;
    }

    // превращаем матрицу в плоский список для GridLayout
    public static List<Cell> flatten(List<List<Cell>> matrix) {
        List<Cell> out = new ArrayList<>();
        for (List<Cell> row : matrix) out.addAll(row);
        return out;
    }
}
