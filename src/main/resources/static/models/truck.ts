import {BoxGeometry, MeshBasicMaterial, TextureLoader, DoubleSide} from 'three'
import {Object3D} from './object3d';

export class Truck extends Object3D {

    /**
     * Constructor
     */
    constructor() {
        const geometry = new BoxGeometry(10, 10, 20);
        const materials = [
            new MeshBasicMaterial({map: new TextureLoader().load('../images/car_side_right.jpg'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/car_side_left.png'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/car_up.png'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/robot_bottom.png'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/car_back.png'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/car_front.png'), side: DoubleSide})
        ];

        super(materials, geometry);
    }
}