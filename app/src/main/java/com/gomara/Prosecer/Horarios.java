package com.gomara.Prosecer;

import com.example.gomara.R;

public class Horarios {

        private String curso, anio;

        public Horarios(String anio, String curso) {
            this.anio = anio;
            this.curso = curso;
        }

        public int Image() {

            if (anio.equals("1")) {
                switch (curso) {
                    case "a":
                        return R.drawable.horario_1a;
                    case "b":
                        return R.drawable.horario_1b;
                    case "c":
                        return R.drawable.horario_1c;
                    case "d":
                        return R.drawable.horario_1d;
                }
            }

            if (anio.equals("2")) {
                switch (curso) {
                    case "a":
                        return R.drawable.horario_2a;
                    case "b":
                        return R.drawable.horario_2b;
                    case "c":
                        return R.drawable.horario_2c;
                    case "d":
                        return R.drawable.horario_2d;
                }
            }

            if (anio.equals("3")) {
                switch (curso) {
                    case "a":
                        return R.drawable.horario_3a;
                    case "b":
                        return R.drawable.horario_3b;
                    case "c":
                        return R.drawable.horario_3c;
                    case "d":
                        return R.drawable.horario_3d;
                }
            }

            if (anio.equals("4")) {
                switch (curso) {
                    case "a":
                        return R.drawable.horario_4a;
                    case "b":
                        return R.drawable.horario_4b;
                    case "c":
                        return R.drawable.horario_4c;
                    case "d":
                        return 1;
                }
            }

            if (anio.equals("5")) {

                switch (curso) {
                    case "a":
                        return R.drawable.horario_5a;
                    case "b":
                        return R.drawable.horario_5b;
                    case "c":
                        return R.drawable.horario_5c;
                    case "d":
                        return 1;
                }

            }

            if (anio.equals("6")) {

                switch (curso) {
                    case "a":
                        return R.drawable.horario_6a;
                }

            }
            return 1;
        }

    }

