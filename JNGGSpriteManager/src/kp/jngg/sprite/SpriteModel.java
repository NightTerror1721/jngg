/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.sprite;

import kp.jngg.sprite.SpriteLoader.RawBitmap;

/**
 *
 * @author Asus
 */
public interface SpriteModel
{
    Sprite buildSprite();
    RawBitmap getRaw();
}
