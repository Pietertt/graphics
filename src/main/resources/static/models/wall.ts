import {BoxGeometry, MeshPhongMaterial, DoubleSide} from 'three'

import  {Object3D} from './object3d';

export class Wall extends Object3D {

    /**
     * Constructor
     * 
     * @param width 
     * @param height 
     * @param depth 
     */
    constructor(width: number, height: number, depth: number) {
        const geometry = new BoxGeometry(width, height, depth);
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