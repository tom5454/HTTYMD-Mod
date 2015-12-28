package com.httymd.entity.dragon;

import com.httymd.entity.EntityDragon;
import com.httymd.entity.dragon.ai.EntityDragonAIWander;

import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntitySkrill extends EntityDragon {

	public EntitySkrill(World world) {
		super(world);
		this.setSize(2, 2F);
		this.stepHeight = 1;
		this.getNavigator().setAvoidsWater(true);
		// Since ground and flight AI can't possibly run concurrently (and they
		// shouldn't), why separate the tasks
		// Also for flight to work, it should probably run first, though it
		// should be fine as long as if isFlying is false
		// Flight
		this.tasks.addTask(4, new EntityDragonAIWander(this));
		// Ground
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.1D, 8F, 40F));
		this.tasks.addTask(4, new EntityAITempt(this, 0.5, Items.fish, true));
		this.tasks.addTask(4, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.ignoreFrustumCheck = true;
		this.setTamed(false);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(healthAtt).setBaseValue(18);
		this.getEntityAttribute(speedAtt).setBaseValue(0.65);
		this.getEntityAttribute(damageAtt).setBaseValue(1.6);
		this.getEntityAttribute(flyingSpeed).setBaseValue(0.2);
	}
}
