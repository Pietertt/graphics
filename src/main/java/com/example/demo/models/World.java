package com.example.demo.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Iterator;

import com.example.demo.models.Robot;

/*
 * Deze class is een versie van het model van de simulatie. In dit geval is het
 * de 3D wereld die we willen modelleren (magazijn). De zogenaamde domain-logic,
 * de logica die van toepassing is op het domein dat de applicatie modelleerd, staat
 * in het model. Dit betekent dus de logica die het magazijn simuleert.
 */
public class World implements Model {
    /*
     * De wereld bestaat uit objecten, vandaar de naam worldObjects. Dit is een lijst
     * van alle objecten in de 3D wereld. Deze objecten zijn in deze voorbeeldcode alleen
     * nog robots. Er zijn ook nog meer andere objecten die ook in de wereld voor kunnen komen.
     * Deze objecten moeten uiteindelijk ook in de lijst passen (overerving). Daarom is dit
     * een lijst van Object3D onderdelen. Deze kunnen in principe alles zijn. (Robots, vrachrtwagens, etc)
     */
    public ArrayList<Object3D> worldObjects;
    public ArrayList<Object3D> queue;

    /*
     * Dit onderdeel is nodig om veranderingen in het model te kunnen doorgeven aan de controller.
     * Het systeem werkt al as-is, dus dit hoeft niet aangepast te worden.
     */
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /*
     * De wereld maakt een lege lijst voor worldObjects aan. Daarin wordt nu één robot gestopt.
     * Deze methode moet uitgebreid worden zodat alle objecten van de 3D wereld hier worden gemaakt.
     */
    public World() {
        this.worldObjects = new ArrayList<Object3D>();
        this.queue = new ArrayList<Object3D>();

        Object3D stellage1 = new Stellage(26, 2, 9);
        Object3D stellage2 = new Stellage(22, 2, 9);
        Object3D stellage3 = new Stellage(10, 2, 9);
        Object3D stellage4 = new Stellage(5, 2, 9);
        Object3D stellage5 = new Stellage(26, 2, 17);
        Object3D stellage6 = new Stellage(21, 2, 17);
        Object3D stellage7 = new Stellage(10, 2, 17);
        Object3D stellage8 = new Stellage(5, 2, 17);
        Object3D stellage9 = new Stellage(21, 2, 24);
        Object3D stellage10 = new Stellage(10, 2, 24);

        this.worldObjects.add(stellage1);
        this.worldObjects.add(stellage2);
        this.worldObjects.add(stellage3);
        this.worldObjects.add(stellage4);
        this.worldObjects.add(stellage5);
        this.worldObjects.add(stellage6);
        this.worldObjects.add(stellage7);
        this.worldObjects.add(stellage8);
        this.worldObjects.add(stellage9);
        this.worldObjects.add(stellage10);

        Object3D robot1 = new Robot(5, 0);
        Object3D robot2 = new Robot(10, 0);

        this.worldObjects.add(robot1);
        this.worldObjects.add(robot2);
        //this.queue.add(new Truck(15, -20));
    }

    /*
     * Deze methode wordt gebruikt om de wereld te updaten. Wanneer deze methode wordt aangeroepen,
     * wordt op elk object in de wereld de methode update aangeroepen. Wanneer deze true teruggeeft
     * betekent dit dat het onderdeel daadwerkelijk geupdate is (er is iets veranderd, zoals een positie).
     * Als dit zo is moet dit worden geupdate, en wordt er via het pcs systeem een notificatie gestuurd
     * naar de controller die luisterd. Wanneer de updatemethode van het onderdeel false teruggeeft,
     * is het onderdeel niet veranderd en hoeft er dus ook geen signaal naar de controller verstuurd
     * te worden.
     */
    @Override
    public void update() {
        Random random = new Random();

        if(random.nextInt(200) == 2){
            if(!this.worldObjectsContainsTruck()){

                Truck truck = new Truck(15, -50);

                for(Object3D object : this.worldObjects){
                    if(object instanceof Robot){
                        truck.addRobot((Robot)object);
                    }
                }

                for(Object3D object : this.worldObjects){
                    if(object instanceof Stellage){
                        truck.addStellage((Stellage)object);
                    }
                }

                this.worldObjects.add(truck);
            }
        }

        for (Object3D object : this.worldObjects) {
            if(object instanceof Updatable) {
                if (((Updatable)object).update()) {
                    if(object.status){
                        pcs.firePropertyChange(Model.UPDATE_COMMAND, null, object); 
                    } else {
                        pcs.firePropertyChange(Model.DELETE_COMMAND, null, object); 
                    }
                }
            }
        }

        for (Iterator<Object3D> iterator = this.worldObjects.iterator(); iterator.hasNext(); ) {
            Object3D value = iterator.next();
            if (!value.status) {
                iterator.remove();
                if(this.queue.size() > 0){
                    Object3D popped = this.queue.get(0);
                    this.queue.remove(0);
                    this.worldObjects.add(popped);
                }
                break;
                // System.out.println(this.worldObjects.size());
            }
        }
        
    }

    /*
     * Standaardfunctionaliteit. Hoeft niet gewijzigd te worden.
     */
    @Override
    public void addObserver(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    /*
     * Deze methode geeft een lijst terug van alle objecten in de wereld. De lijst is echter wel
     * van ProxyObject3D objecten, voor de veiligheid. Zo kan de informatie wel worden gedeeld, maar
     * kan er niks aangepast worden.
     */
    @Override
    public List<Object3D> getWorldObjectsAsList() {
        ArrayList<Object3D> returnList = new ArrayList<>();

        for(Object3D object : this.worldObjects) {
            returnList.add(object);
        }

        return returnList;
    }

    public boolean worldObjectsContainsTruck(){
        for(Object3D object : this.worldObjects){
            if(object instanceof Truck){
                return true;
            }
        }

        return false;
    }
}