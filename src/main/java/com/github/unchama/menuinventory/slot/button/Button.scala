package com.github.unchama.menuinventory.slot.button

import org.bukkit.inventory.ItemStack

/**
 * メニューインベントリ上で「ボタン」として動作する[Slot]のクラス.
 *
 * [effects]により与えられる作用をリストの順で一つづつ実行していきます.
 *
 * [effectOn]は常に与えられた[InventoryClickEvent]をキャンセルする副作用を含みます.
 *
 * @param itemStack  [Inventory] へセットする [ItemStack]
 * @author karayuu
 */
case class Button(override val itemStack: ItemStack,
                  private val effects: List[ButtonEffect]) : Slot {

  /**
   * [effects]をひとつずつ作用として発生させる [Slot] を構築します.
   */
  def this(itemStack: ItemStack, vararg effects: ButtonEffect): this(itemStack, effects.toList())

  override def effectOn(event: InventoryClickEvent): TargetedEffect[Player] =
      UnfocusedEffect { event.isCancelled = true } + this.effects.map { it.asyncEffectOn(event) }.asSequentialEffect()

  def withAnotherEffect(effect: ButtonEffect): Button = this.copy(effects = effects + effect)

}
