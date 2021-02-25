import {BoxGeometry, MeshBasicMaterial, TextureLoader, DoubleSide} from 'three'
import {Object3D} from './object3d';

export class Robot extends Object3D {

    /**
     * Constructor
     */
    constructor() {
        const geometry = new BoxGeometry(0.9, 0.3, 0.9);
        const materials = [
            new MeshBasicMaterial({map: new TextureLoader().load('../images/robot_side.png'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/robot_side.png'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/robot_top.png'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/robot_bottom.png'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/robot_front.png'), side: DoubleSide}),
            new MeshBasicMaterial({map: new TextureLoader().load('../images/robot_front.png'), side: DoubleSide})
        ];
        super(materials, geometry);
    }
}