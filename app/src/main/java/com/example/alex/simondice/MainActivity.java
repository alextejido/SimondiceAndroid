package com.example.alex.simondice;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.os.Handler;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity  {
    Button verde;
    Button rojo;
    Button amarillo;
    Button azul;
    Button comprobar;
    Button botoncolores;
    ArrayList game = new ArrayList();
    ArrayList<Integer> answers = new ArrayList();
    Button botonescolor[];
    int turnosTotales = 0;
    int posicionCheck = 0;
    int[] colores = new int[50];
    boolean esCorrecto = false;
    int posicionSecuencia = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verde = (Button) findViewById(R.id.verde);
        rojo = (Button) findViewById(R.id.rojo);
        amarillo = (Button) findViewById(R.id.amarillo);
        azul = (Button) findViewById(R.id.azul);
        comprobar = (Button) findViewById(R.id.comprobar);
        botoncolores = (Button) findViewById(R.id.botoncolores);




        verde.setAlpha(0.5f);
        amarillo.setAlpha(0.5f);
        azul.setAlpha(0.5f);
        rojo.setAlpha(0.5f);

        findViewById(R.id.comprobar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaPartida();
            }
        });
        findViewById(R.id.amarillo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generarSecuencia();

            }
        });
        azul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarColor(Color.BLUE);
                iluminarBoton(azul);
            }
        });
        rojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarColor(Color.RED);
                iluminarBoton(rojo);
            }
        });
        verde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarColor(Color.GREEN);
                iluminarBoton(verde);
            }
        });
        amarillo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comprobarColor(Color.YELLOW);
                iluminarBoton(amarillo);
            }
        });
        botoncolores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generarSecuencia();
            }
        });

    }


    public void nuevaPartida() {

        findViewById(R.id.botoncolores).setBackgroundColor(Color.GRAY);
        turnosTotales = 0;
        Button botonStart = (Button) findViewById(R.id.start);
        botonStart.setEnabled(true);
        botoncolores.setEnabled(true);
        TextView t = (TextView) findViewById(R.id.ronda);
        t.setText("Turno: " + turnosTotales);
    }

    public void actualizacolor(int color) {
        View fondo = findViewById(R.id.botoncolores);
        fondo.setBackgroundColor(color);
    }

    public void generarSecuencia() {
        Random rd = new Random();
        int[] coloresDisponibles = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        colores[turnosTotales] = coloresDisponibles[rd.nextInt(4)];
        TextView t = (TextView) findViewById(R.id.ronda);
        t.setText("Turno: " + (turnosTotales + 1));
        turnosTotales++;
        mostrarSecuencia();
        esCorrecto = true;
        posicionCheck = 0;
    }

    public void mostrarSecuencia() {
        final View fondo = findViewById(R.id.botoncolores);
        posicionSecuencia = 0;
        for (int i = 0; i < turnosTotales; i++) {
            if (colores[i] != 0) {
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        actualizacolor(colores[posicionSecuencia]);
                        fondo.setAlpha(0.2f);
                    }
                }, (i + 1) * 500);

                //cambios de brillo aqui dentro
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.5f);
                    }
                }, (500 * (i + 1)) + 100);
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.9f);
                    }
                }, (500 * (i + 1)) + 150);

                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(1f);
                    }
                }, (500 * (i + 1)) + 250);
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.9f);
                    }
                }, (500 * (i + 1)) + 350);
                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.5f);
                    }
                }, (500 * (i + 1)) + 400);

                fondo.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fondo.setAlpha(0.2f);
                        posicionSecuencia++;
                    }
                }, (500 * (i + 1)) + 450);

            }

        }
        fondo.postDelayed(new Runnable() {
            @Override
            public void run() {
               actualizacolor(Color.GRAY);
                fondo.setAlpha(1f);
                amarillo.setEnabled(true);
                azul.setEnabled(true);
                rojo.setEnabled(true);
                verde.setEnabled(true);
            }
        }, (turnosTotales + 1) * 500);

        Button botonStart = (Button) findViewById(R.id.start);
        botonStart.setEnabled(false);
        botoncolores.setEnabled(false);
    }

    public void comprobarColor(int color) {
        Button botonStart = (Button) findViewById(R.id.start);
        final Button perder = (Button) findViewById(R.id.botoncolores);
        if (color == colores[posicionCheck]) {
            posicionCheck++;
        } else {
            esCorrecto = false;
        }

        if (posicionCheck == turnosTotales && esCorrecto) {

            botonStart.setEnabled(true);
            botoncolores.setEnabled(true);
            amarillo.setEnabled(false);
            azul.setEnabled(false);
            rojo.setEnabled(false);
            verde.setEnabled(false);

        }
        if (!esCorrecto && turnosTotales > 0) {
            perder.postDelayed(new Runnable() {
                @Override
                public void run() {
                    azul.setVisibility(View.INVISIBLE);
                    rojo.setVisibility(View.INVISIBLE);
                    amarillo.setVisibility(View.INVISIBLE);
                    verde.setVisibility(View.INVISIBLE);
                    TextView t = (TextView) findViewById(R.id.ronda);
                    t.setVisibility(View.INVISIBLE);
                    perder.setBackgroundColor(Color.WHITE);
                    perder.setText("HAS PERDIDO");
                    perder.setScaleX(3f);
                    perder.setScaleY(3f);
                }
            }, 50);
            perder.postDelayed(new Runnable() {
                @Override
                public void run() {
                    perder.setText("");
                    perder.setScaleX(1f);
                    perder.setScaleY(1f);
                    perder.setBackgroundColor(Color.GRAY);
                    TextView t = (TextView) findViewById(R.id.ronda);
                    t.setText("Turno: 0");
                    azul.setVisibility(View.VISIBLE);
                    rojo.setVisibility(View.VISIBLE);
                    amarillo.setVisibility(View.VISIBLE);
                    verde.setVisibility(View.VISIBLE);
                    t.setVisibility(View.VISIBLE);
                }
            }, 1500);


            nuevaPartida();
            botonStart.setEnabled(true);
        }
    }

    public void iluminarBoton(Button boton) {
        final Button b = boton;
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.5f);
            }
        }, 0);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.75f);
            }
        }, 50);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(1f);
            }
        }, 100);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.75f);
            }
        }, 250);
        b.postDelayed(new Runnable() {
            @Override
            public void run() {
                b.setAlpha(0.5f);
            }
        }, 300);
    }

}

