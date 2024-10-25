package com.example.hw4jsppizzaapp.Services;

import com.example.hw4jsppizzaapp.Models.Position;

import java.util.*;

public class CalculatePositionService {
    private static final Random random;
    private static int min;
    private static int max;

    static {
        random = new Random();
        min = 10;
        max = 80;
    }

    public static List<Position> getPositions(int numberOfPositions) {
        HashSet<Position> positions = new HashSet<>();


        while (positions.size() < numberOfPositions) {
            int left = random.nextInt((max - min) + 1) + min;
            int top = random.nextInt((max - min) + 1) + min;

            int coinFlip = random.nextInt(2);
            int threeNumber = random.nextInt(3);
            int rotate;

            switch (threeNumber) {
                case 0:
                    rotate = 30;
                    break;
                case 1:
                    rotate = 45;
                    break;
                default:
                    rotate = 60;
                    break;
            }

            rotate = coinFlip == 0 ? rotate : rotate * -1;

            positions.add(new Position(left, top, rotate));
        }

        return new ArrayList<>(positions);
    }
}
