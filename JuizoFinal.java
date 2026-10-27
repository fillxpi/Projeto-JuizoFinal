package PastaJuizoFinal;
import robocode.AdvancedRobot;
import robocode.*;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html
/**
 * JuizoFinal - a robot by (your name here)
 */
public class JuizoFinal extends AdvancedRobot
{
    // guarda os dados de cada inimigo já escaneado (chave = nome do inimigo)
    Map<String, Inimigo> inimigos = new HashMap<String, Inimigo>();
    // nome do inimigo escolhido como alvo (null = nenhum)
    String alvo = null;

    /**
     * run: JuizoFinal's default behavior
     */
    public void run() {
    // aqui to só definindo as cores do robô
    setGunColor(new Color(240, 208, 221));
    setBodyColor(new Color(240, 208, 221));
    setRadarColor(new Color(240, 208, 221));
	
        while(true) {
            // Replace the next 4 lines with any behavior you would like
            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }
    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // guarda/atualiza os dados desse inimigo
        Inimigo i = inimigos.get(e.getName());
        if (i == null) {
            i = new Inimigo();
            i.nome = e.getName();
            inimigos.put(e.getName(), i);
        }
        double anguloAbsoluto = getHeadingRadians() + e.getBearingRadians();
        i.x = getX() + Math.sin(anguloAbsoluto) * e.getDistance();
        i.y = getY() + Math.cos(anguloAbsoluto) * e.getDistance();
        i.energia = e.getEnergy();
        i.velocidade = e.getVelocity();
        i.rumo = e.getHeadingRadians();
        i.visto = getTime();

        // reavalia quem é o alvo com os dados mais recentes
        escolherAlvo();

        // só atira se o robô escaneado for o alvo escolhido
        if (e.getName().equals(alvo)) {
            fire(1);
        }
    }
    static class Inimigo {
    String nome;
    double x, y, energia, velocidade,rumo;
    long visto; // ultima vez q foi escaneado
    }
    // considera "recente" o inimigo visto nos últimos 40 ticks
    boolean recente(Inimigo i) {
        return getTime() - i.visto <= 40;
    }
    // escolhe como alvo o inimigo com MENOS energia (vida)
    public void escolherAlvo () {
        Inimigo melhor = null;
        double menor = Double.MAX_VALUE;
        for (Inimigo i : inimigos.values()) {
            if (!recente(i)) continue;
            if (i.energia < menor) {
                menor = i.energia;
                melhor = i;
            }
        }
        alvo = (melhor == null) ? null : melhor.nome;
    }
    /**
     * onRobotDeath: remove o inimigo morto da lista
     */
    public void onRobotDeath(RobotDeathEvent e) {
        inimigos.remove(e.getName());
        if (e.getName().equals(alvo)) {
            alvo = null;
        }
    }
    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        back(10);
    }
    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        back(20);
    }    
}