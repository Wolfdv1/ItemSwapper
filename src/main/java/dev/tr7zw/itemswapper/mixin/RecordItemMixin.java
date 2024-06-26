package dev.tr7zw.itemswapper.mixin;

import java.util.HashSet;
import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;

import dev.tr7zw.itemswapper.accessor.ItemVariantAccess;

import net.minecraft.world.item.Item;

//spotless:off
//#if MC >= 12100
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.core.component.DataComponents;

@Mixin(Items.class)
public class RecordItemMixin implements ItemVariantAccess {
    private static Set<Item> BY_NAME = new HashSet<>();

    @Inject(method = "registerItem(Ljava/lang/String;Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/item/Item;", at = @At("HEAD"))
    private static void registerItemMixin(String key, Item item, CallbackInfoReturnable<Item> cir) {
        if (BY_NAME == null) { BY_NAME = new HashSet<>(); }
        if (item.components().has(DataComponents.JUKEBOX_PLAYABLE)) {
            BY_NAME.add(item);
        };
    }

    @Override
    public Set<Item> getAllItemVariants() {
        return BY_NAME;
    }
}

//#else
//$$ import net.minecraft.sounds.SoundEvent;
//$$ import net.minecraft.world.item.RecordItem;
//$$ import java.util.Map;
//$$ import org.spongepowered.asm.mixin.Shadow;
//$$
//$$ @Mixin(RecordItem.class)
//$$ public class RecordItemMixin implements ItemVariantAccess {
//$$
//$$     @Shadow
//$$     private static Map<SoundEvent, RecordItem> BY_NAME;
//$$
//$$     @Override
//$$     public Set<Item> getAllItemVariants() {
//$$         return new HashSet<>(BY_NAME.values());
//$$     }
//$$
//$$ }
//#endif
//spotless:on