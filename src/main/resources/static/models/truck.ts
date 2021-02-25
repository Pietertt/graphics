import {WebGLRenderer, PerspectiveCamera, Scene, Color, Group} from 'three';
import {DirectionalLight, MaterialLoader, Loader} from 'three'
import {PlaneGeometry, BoxGeometry, MeshBasicMaterial, MeshPhongMaterial, TextureLoader, DoubleSide, Mesh} from 'three'
import { OBJLoader } from 'three/examples/jsm/loaders/OBJLoader.js';
import { MTLLoader } from 'three/examples/jsm/loaders/MTLLoader.js';

import { Command } from '../models/command';
import { Object3D } from './object3d';

export class Truck extends Object3D {

    constructor() {
        const geometry = new BoxGeometry(1.0, 1.0, 5.0);
        const materials: MeshPhongMaterial[] = [];

        let mtlLoader: MTLLoader = new MTLLoader();
        mtlLoader.setPath('../images/models/');
        mtlLoader.load('car.mtl', function(materials) {
            materials.preload();
            let objLoader: OBJLoader = new OBJLoader();
            objLoader.setMaterials(materials);
            objLoader.setPath('../images/models/');
            objLoader.load('car.obj', function(object) {
                let truck: Group = object;
                truck.scale.set(0.8, 0.8, 0.8);
                truck.rotation.y = 36.15;
                let group: Group = new Group();
                group.add(truck);
            });
        });

        super(materials, geometry);
    }
}