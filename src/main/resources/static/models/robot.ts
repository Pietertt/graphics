import {WebGLRenderer, PerspectiveCamera, Scene, Color, Group} from 'three';
import {DirectionalLight} from 'three'
import {PlaneGeometry, BoxGeometry, MeshBasicMaterial, TextureLoader, DoubleSide, Mesh} from 'three'
import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls';

import { Command } from '../models/command';
import { Object3D } from './object3d';

export class Robot extends Object3D {

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