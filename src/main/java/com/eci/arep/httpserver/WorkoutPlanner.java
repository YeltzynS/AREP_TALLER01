package com.eci.arep.httpserver;

import java.util.*;

public class WorkoutPlanner {

    private static final Map<String, String[]> WORKOUTS = new HashMap<>();

    static {
        WORKOUTS.put("strength-beginner", new String[] {
            "Sentadillas - 3 series de 10 repeticiones",
            "Flexiones - 3 series de 8 repeticiones",
            "Peso muerto con mancuerna - 3 series de 10 repeticiones"
        });

        WORKOUTS.put("strength-intermediate", new String[] {
            "Dominadas - 3 series de 8 repeticiones",
            "Press de banca - 3 series de 10 repeticiones",
            "Peso muerto - 3 series de 10 repeticiones"
        });

        WORKOUTS.put("strength-advanced", new String[] {
            "Clean and Jerk - 3 series de 5 repeticiones",
            "Sentadilla con barra - 3 series de 10 repeticiones",
            "Press militar - 3 series de 8 repeticiones"
        });

        WORKOUTS.put("cardio-beginner", new String[] {
            "Caminata rápida - 30 minutos",
            "Saltar la cuerda - 3 series de 1 minuto",
            "Burpees - 3 series de 10 repeticiones"
        });

        WORKOUTS.put("cardio-intermediate", new String[] {
            "Carrera de 5 km",
            "Sprint 100m x 5 series",
            "Jumping Jacks - 3 series de 1 minuto"
        });

        WORKOUTS.put("cardio-advanced", new String[] {
            "Carrera de 10 km",
            "Sprints de 200m x 8 series",
            "Burpees con salto - 3 series de 15 repeticiones"
        });

        WORKOUTS.put("flexibility-beginner", new String[] {
            "Estiramiento de piernas - 10 minutos",
            "Estiramiento de espalda - 5 minutos",
            "Postura del perro boca abajo (yoga) - 1 minuto"
        });

        WORKOUTS.put("flexibility-intermediate", new String[] {
            "Postura del guerrero (yoga) - 3 minutos",
            "Estiramiento de isquiotibiales - 5 minutos",
            "Estiramiento de espalda profunda - 5 minutos"
        });

        WORKOUTS.put("flexibility-advanced", new String[] {
            "Postura del loto completa (yoga) - 5 minutos",
            "Estiramiento en split - 10 minutos",
            "Puente de espalda - 3 minutos"
        });
    }

    public static String[] getWorkout(String type, String level) {
        if ("running".equals(type)) {
            type = "cardio";
        }

        String key = type + "-" + level;
        return WORKOUTS.getOrDefault(key, new String[]{"Rutina no encontrada."});
    }
}
