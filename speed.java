package me.libayon.battlebonk.module.modules.movement;

import net.minecraft.client.entity.*;
import me.libayon.battlebonk.module.*;
import me.libayon.battlebonk.event.events.*;
import net.minecraft.network.*;

public class Speed extends Module
{
    public int times;
    public int jumpticks;
    private boolean jumped;
    public int ticks;
    public EntityPlayerSP player;
    
    public Speed() {
        super("Speed", "Allows you to BHop", 44, Category.MOVEMENT, new Mode[] { Mode.AAC, Mode.VULCAN, Mode.LUCKYNETWORK, Mode.BATTLEASYAKITPVP });
        this.jumpticks = 0;
        this.jumped = false;
    }
    
    @Override
    public void setup() {
    }
    
    @Override
    public void onEnable() {
        this.jumped = false;
        this.jumpticks = 0;
        this.ticks = 0;
        this.player = this.mc.thePlayer;
        this.times = 0;
    }
    
    @Override
    public void onDisable() {
        this.mc.timer.timerSpeed = 1.0f;
    }
    
    @Override
    public void onJump(final JumpEvent e) {
    }
    
    @Override
    public void onUpdate(final UpdateEvent event) {
        final Mode m = this.getMode();
        switch (m) {
            case VULCAN: {
                this.mc.gameSettings.keyBindJump.setPressed(false);
                this.mc.thePlayer.setSprinting(true);
                if (this.mc.thePlayer.onGround) {
                    this.jumpticks = 0;
                    this.jumped = true;
                    this.mc.thePlayer.jump();
                }
                if (this.jumped) {
                    ++this.jumpticks;
                }
                if (this.jumpticks > 4) {
                    this.mc.thePlayer.motionY = -0.1;
                    this.jumped = false;
                    this.jumpticks = 0;
                    break;
                }
                break;
            }
            case AAC: {
                this.mc.gameSettings.keyBindJump.setPressed(false);
                this.mc.thePlayer.setSprinting(true);
                if (this.mc.thePlayer.onGround) {
                    this.jumpticks = 0;
                    this.jumped = true;
                    this.mc.thePlayer.jump();
                }
                if (this.jumped) {
                    ++this.jumpticks;
                }
                if (this.jumpticks > 4) {
                    this.mc.thePlayer.motionY = -0.05;
                    this.jumped = false;
                    this.jumpticks = 0;
                    break;
                }
                break;
            }
            case BATTLEASYAKITPVP: {
                this.mc.gameSettings.keyBindJump.setPressed(false);
                this.mc.thePlayer.setSprinting(true);
                if (this.mc.thePlayer.onGround) {
                    this.jumpticks = 0;
                    this.jumped = true;
                    this.mc.thePlayer.jump();
                }
                if (this.jumped) {
                    ++this.jumpticks;
                }
                if (this.jumpticks == 6) {
                    this.mc.thePlayer.motionY = -0.15;
                }
                if (this.jumpticks > 7) {
                    this.mc.thePlayer.motionY = 1.0;
                }
                if (this.jumpticks == 8) {
                    this.jumped = false;
                    this.jumpticks = 0;
                    break;
                }
                break;
            }
            case VERUSRECODED: {
                this.mc.gameSettings.keyBindJump.setPressed(false);
                this.mc.thePlayer.setSprinting(true);
                if (this.mc.thePlayer.onGround) {
                    this.jumpticks = 0;
                    this.jumped = true;
                    this.mc.thePlayer.jump();
                }
                if (this.jumped) {
                    ++this.jumpticks;
                }
                if (this.jumpticks > 5) {
                    this.mc.thePlayer.motionY = -0.2;
                    this.jumped = false;
                    this.jumpticks = 0;
                    break;
                }
                break;
            }
        }
    }
    
    @Override
    public void onRender3D(final Render3DEvent e) {
    }
    
    @Override
    public void onPacketReceived(final PacketReceivedEvent event) {
        final Packet p = event.getPacket();
    }
    
    public void speed(final double speed) {
        final double yaw = Math.toRadians(this.mc.thePlayer.rotationYaw);
        this.mc.thePlayer.motionX = -Math.sin(yaw) * speed;
        this.mc.thePlayer.motionZ = Math.cos(yaw) * speed;
        
