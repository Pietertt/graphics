import {WebGLRenderer, PerspectiveCamera, Scene, Color, Group} from 'three';
import {DirectionalLight} from 'three'
import {PlaneGeometry, BoxGeometry, MeshBasicMaterial, TextureLoader, DoubleSide, Mesh} from 'three'
import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls';

import {Command} from '../models/command';

export class Object3D extends Mesh {

    private UUID: string;


    constructor(materials: MeshBasicMaterial[], geometry: BoxGeometry) {
        super(geometry, materials);
    }

    public spawn(): Group {
        let group: Group = new Group();
        group.add(this);
        return group;

    }

    public setUUID(UUID: string): void {
        this.UUID = UUID;
    }

    public getUUID(): string {
        return this.UUID;
    }

    public setRotationX(rotation: number): void {
        this.rotation.x = rotation;
    }

    public setRotationy(rotation: number): void {
        this.rotation.y = rotation;
    }

    public setRotationZ(rotation: number): void {
        this.rotation.z = rotation;
    }

    public setPositionX(position: number): void {
        this.position.x = position;
    }

    public setPositionY(position: number): void {
        this.position.y = position;
    }

    public setPositionZ(position: number): void {
        this.position.z = position;
    }
}