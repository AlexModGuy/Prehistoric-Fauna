package superlord.prehistoricfauna.items;

import superlord.prehistoricfauna.Main;
import superlord.prehistoricfauna.entity.egg.EntityAllosaurusEgg;
import superlord.prehistoricfauna.entity.egg.EntityCamarasaurusEgg;
import superlord.prehistoricfauna.entity.egg.EntityDinoEgg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemAllosaurusEgg extends ItemBase {
    public ItemAllosaurusEgg(String name) {
        super(name);
        this.maxStackSize = 16;
        this.setCreativeTab(Main.tabPrehistoric);
    }

    /**
     * Called when the equipped item is right clicked.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.capabilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote) {
            EntityAllosaurusEgg entityegg = new EntityAllosaurusEgg(worldIn, playerIn);
            entityegg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entityegg);
        }

        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}