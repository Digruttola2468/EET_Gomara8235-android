package com.gomara.Prosecer;

import com.example.gomara.R;

public class Horarios {

        private String curso, anio;

        public Horarios(String anio, String curso) {
            this.anio = anio;
            this.curso = curso;
        }

        public String getModalidad(){
            //TODO obtener de Firebase si es "TALLER ELECTRONICA"
            return "";
        }

        public String Image() {

            if (anio.equals("1")) {
                switch (curso) {
                    case "a":
                        return "Horarios/horario_1a.png";
                    case "b":
                        return "Horarios/horario_1b.png";
                    case "c":
                        return "Horarios/horario_1c.png";
                    case "d":
                        return "Horarios/horario_1d.png";
                }
            }

            if (anio.equals("2")) {
                switch (curso) {
                    case "a":
                        return "Horarios/horario_2a.png";
                    case "b":
                        return "Horarios/horario_2b.png";
                    case "c":
                        return "Horarios/horario_2c.png";
                    case "d":
                        return "Horarios/horario_2d.png";
                }
            }

            if (anio.equals("3")) {
                switch (curso) {
                    case "a":
                        return "Horarios/horario_3a.png";
                    case "b":
                        return "Horarios/horario_3b.png";
                    case "c":
                        return "Horarios/horario_3c.png";
                    case "d":
                        return "Horarios/horario_3d.png";
                }
            }

            if (anio.equals("4")) {
                switch (curso) {
                    case "a":
                        return "Horarios/horario_4a.png";
                    case "b":
                        return "Horarios/horario_4b.png";
                    case "c":
                        return "Horarios/horario_4c.png";
                    case "d":
                        return "";
                }
            }

            if (anio.equals("5")) {

                switch (curso) {
                    case "a":
                        return "Horarios/horario_5a.png";
                    case "b":
                        return "Horarios/horario_5b.png";
                    case "c":
                        return "Horarios/horario_5c.png";
                    case "d":
                        return "";
                }

            }

            if (anio.equals("6")) {

                switch (curso) {
                    case "a":
                        return "Horarios/horario_6a.png";
                }

            }
            return "";
        }

    }

