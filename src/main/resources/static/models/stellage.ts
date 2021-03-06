import {BoxGeometry, MeshPhongMaterial, DoubleSide} from 'three'
import {Object3D} from './object3d';

export class Stellage extends Object3D {

    /**
     * Constructor
     */
    constructor() {
        const geometry = new BoxGeometry(1.0, 1.0, 5.0);
        const materials = [
            new MeshPhongMaterial({color: 0x0000ff, side: DoubleSide }),
            new MeshPhongMaterial({color: 0x0000ff, side: DoubleSide }),
            new MeshPhongMaterial({color: 0x0000ff, side: DoubleSide }),
            new MeshPhongMaterial({color: 0x0000ff, side: DoubleSide }),
            new MeshPhongMaterial({color: 0x0000ff, side: DoubleSide }),
            new MeshPhongMaterial({color: 0x0000ff, side: DoubleSide })
        ];
        super(materials, geometry);
    }
}