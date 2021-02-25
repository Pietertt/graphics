import {WebGLRenderer, PerspectiveCamera, Scene, Color, Group, Material} from 'three';
import {DirectionalLight} from 'three'
import {BoxGeometry, Mesh} from 'three'

export class Object3D extends Mesh {

    private UUID: string;


    constructor(materials: Material[], geometry: BoxGeometry) {
        super(geometry, materials);
    }

    /**
     * Add the object to a group and return it
     */
    public spawn(): Group {
        let group: Group = new Group();
        group.add(this);
        return group;

    }

    /**
     * Set the UUID
     * 
     * @param UUID 
     */
    public setUUID(UUID: string): void {
        this.UUID = UUID;
        this.name = UUID;
    }

    /**
     * Get the UUID
     * 
     * @return string
     */
    public getUUID(): string {
        return this.UUID;
    }

    /**
     * Set the x rotation
     * 
     * @param rotation 
     */
    public setRotationX(rotation: number): void {
        this.rotation.x = rotation;
    }

    /**
     * Set the y rotation
     * 
     * @param rotation 
     */
    public setRotationY(rotation: number): void {
        this.rotation.y = rotation;
    }

    /**
     * Set the z rotation
     * 
     * @param rotation 
     */
    public setRotationZ(rotation: number): void {
        this.rotation.z = rotation;
    }

    /**
     * Set the x position
     * 
     * @param position 
     */
    public setPositionX(position: number): void {
        this.position.x = position;
    }

    /**
     * Set the y position
     * 
     * @param position 
     */
    public setPositionY(position: number): void {
        this.position.y = position;
    }

    /**
     * Set the z position
     * 
     * @param position 
     */
    public setPositionZ(position: number): void {
        this.position.z = position;
    }
}