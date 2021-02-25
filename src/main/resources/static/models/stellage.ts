import {WebGLRenderer, PerspectiveCamera, Scene, Color, Group} from 'three';
import {DirectionalLight} from 'three'
import {PlaneGeometry, BoxGeometry, MeshBasicMaterial, MeshPhongMaterial, TextureLoader, DoubleSide, Mesh} from 'three'
import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls';

import { Command } from '../models/command';
import { Object3D } from './object3d';

export class Stellage extends Object3D {

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