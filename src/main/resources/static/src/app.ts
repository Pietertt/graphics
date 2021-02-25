import {WebGLRenderer, PerspectiveCamera, Scene, Color} from 'three';
import {DirectionalLight} from 'three'
import {PlaneGeometry, MeshBasicMaterial, TextureLoader, DoubleSide, Mesh, WebGLCubeRenderTarget} from 'three'
import {OrbitControls} from 'three/examples/jsm/controls/OrbitControls';

import {Command} from '../models/command';
import {Object3D} from '../models/object3d';
import {Robot} from '../models/robot';
import {Stellage} from '../models/stellage';
import {Truck} from '../models/truck';
import {Wall} from '../models/wall';

class App {
    private fov: number;
    private aspect: number;
    private near: number;
    private far: number;

    private canvas: HTMLCanvasElement;

    private renderer: WebGLRenderer;
    private camera: PerspectiveCamera;
    private controls: OrbitControls;

    private socket: WebSocket;

    private scene: Scene;

    private worldObjects: Object3D[] = [];

    /**
     * Constructor
     */
    constructor() {
        this.fov = 50;
        this.aspect = window.innerWidth / window.innerHeight;
        this.near = 5;
        this.far = 3000;
    }

    /**
     * Initializes the game
     */
    public init() : void {
        const canvas: HTMLCanvasElement = document.querySelector<HTMLCanvasElement>('#c');
        this.canvas = canvas;

        this.renderer = new WebGLRenderer({canvas});
        this.renderer.setPixelRatio(window.devicePixelRatio);
        this.renderer.setSize(window.innerWidth, window.innerHeight + 5);

        this.camera = new PerspectiveCamera(this.fov, this.aspect, this.near, this.far);
        this.camera.position.set(-70, 70, 50);

        this.controls = new OrbitControls(this.camera, this.canvas);
        this.controls.target.set(4, 10, -4);
        this.controls.update();

        this.scene = new Scene();
        this.scene.background = new Color('black');

        this.socket = new WebSocket('ws://' + window.location.hostname + ':8080/connectToSimulation');
        this.socket.onmessage = this.handleRequest.bind(this);
    
        this.addLighting();
        this.addEnvironment();
    }

    /**
     * Adds lightning to the game
     */
    private addLighting(): void {
        const directionalLight = new DirectionalLight(0xFFFFFF, 5);
        directionalLight.position.set(0, 10, 20);
        directionalLight.target.position.set(-5, 0, 0);
        this.scene.add(directionalLight);
        this.scene.add(directionalLight.target);
    }

    /**
     * Adds a floor, walls and a skybox to the game
     */
    private addEnvironment(): void {
        const geometry: PlaneGeometry = new PlaneGeometry(30, 37, 37);
        const material: MeshBasicMaterial = new MeshBasicMaterial({
            map: new TextureLoader().load('images/floor.png'),
            side: DoubleSide
        });

        let east: Wall = new Wall(1.0, 5.0, 37.0);
        east.setPositionZ(23);
        east.setPositionY(2);
        this.scene.add(east);

        let west: Wall = new Wall(1.0, 5.0, 37.0);
        west.setPositionZ(23);
        west.setPositionX(33);
        west.setPositionY(2);
        this.scene.add(west);

        let north: Wall = new Wall(1.0, 5.0, 34);
        north.setPositionZ(42);
        north.setRotationY(1.57);
        north.setPositionX(16.6);
        north.setPositionY(2);
        this.scene.add(north);

        let plane: Mesh = new Mesh(geometry, material);
        plane.rotation.x = Math.PI / 2.0;
        plane.position.x = 17;
        plane.position.z = 23;
        this.scene.add(plane);

        const loader = new TextureLoader();
        const texture = loader.load('images/sky.jpg', () => {
            const image = new WebGLCubeRenderTarget(texture.image.height);
            image.fromEquirectangularTexture(this.renderer, texture);
            this.scene.background = image;
          });
    }

    /**
     * The animate loop
     */
    public animate(): void {
        requestAnimationFrame(() => this.animate());
        this.renderer.render( this.scene, this.camera );
        this.controls.update();
    }

    /**
     * Adds a 3D object to the objects list
     * 
     * @param object 
     */
    private addObject3D(object: Object3D): void {
        this.worldObjects.push(object);
    }

    /**
     * Removes an object from the objects list and from the scene
     * 
     * @param uuid 
     */
    private removeObject3D(uuid: string): void {
        let index: number = this.worldObjects.findIndex(object => object.getUUID() === uuid);
        if (index !== -1) {
            console.log("[GAME] Removed object");
            this.worldObjects.splice(index, 1);
            this.scene.remove(this.scene.getObjectByName(uuid));
            this.animate();
        }
    }

    /**
     * Handles any incoming socket request. Not existing objects are spawned and added to the world
     * Objects are updated and removed
     * 
     * @param event 
     */
    public handleRequest(event: any): void {
        const c = JSON.parse(event.data);

        let command: Command = new Command();
       
        command.command = c.command;
        command.UUID = c.parameters.uuid;
        command.type = c.parameters.type;
        command.rotationX = c.parameters.rotationX;
        command.rotationY = c.parameters.rotationY;
        command.rotationZ = c.parameters.rotationZ;
        command.x = c.parameters.x;
        command.y = c.parameters.y;
        command.z = c.parameters.z;
        
        if (command.command === 'object_update') {

            if (this.worldObjects.findIndex(object => object.getUUID() === command.UUID) === -1) {

                if (command.type === 'robot') {
                    let robot: Robot = new Robot();
                    robot.setUUID(command.UUID);
                    this.scene.add(robot.spawn());
                    this.addObject3D(robot);
                }

                if (command.type === 'stellage') {
                    let stellage: Stellage = new Stellage();
                    stellage.setUUID(command.UUID);
                    stellage.setRotationX(Math.PI / 2.0);
                    this.scene.add(stellage.spawn());
                    this.addObject3D(stellage);
                }

                if (command.type === 'truck') {
                    let truck: Truck = new Truck();
                    truck.setUUID(command.UUID);
                    this.scene.add(truck.spawn());
                    this.addObject3D(truck);
                }
            }

            let object: Object3D = this.worldObjects[this.worldObjects.findIndex(object => object.getUUID() === command.UUID)];
            
            if (object !== undefined) {
                object.setPositionX(command.x);
                object.setPositionY(command.y);
                object.setPositionZ(command.z);
            }
        } else if (command.command === 'object_delete') {
            this.removeObject3D(command.UUID);
        }
    }
}

let app: App = new App();
app.init();
app.animate();