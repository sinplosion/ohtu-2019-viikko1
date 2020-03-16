package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void varastostaNegatiivinenArvo() {
        assertTrue(varasto.otaVarastosta(-1) == 0);
    }

    @Test
    public void varastostaVainMaksimissaanSaldo() {
        varasto.lisaaVarastoon(1);
        assertTrue(1 == varasto.otaVarastosta(2));
    }
    @Test
    public void merkkijonoesitysOikein() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }

    @Test
    public void negatiivinenArvoLisaaVarastoon() {
        varasto.lisaaVarastoon(-1);
        assertTrue(varasto.getSaldo() == 0);
    }


    @Test
    public void yliTilavuudenArvoLisaaVarastoon() {
        varasto.lisaaVarastoon(varasto.getTilavuus() * 2);
        assertTrue(varasto.getSaldo() == varasto.getTilavuus());
    }

    @Test
    public void konstruktoriNegatiivisellaTilavuudella() {
        varasto = new Varasto(-1);
        assertTrue(varasto.getTilavuus() == 0);
    }

    @Test
    public void konstruktoriNegatiivisellaTilavuudellaPositiivisellaSaldolla() {
        varasto = new Varasto(-1, 1);
        assertTrue(varasto.getTilavuus() == 0);

        
    }

    @Test
    public void konstruktoriPosTilavuudellaNegSaldolla(){
        varasto = new Varasto(1, -1);
        assertTrue(varasto.getSaldo() == 0);
    }

    @Test
    public void konstruktoriSaldoJaTilavuusSamat(){
        varasto = new Varasto(2,2);
        assertTrue(varasto.getSaldo() == 3);
    }








}