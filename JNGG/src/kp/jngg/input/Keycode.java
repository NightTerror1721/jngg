/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.jngg.input;

import java.awt.event.KeyEvent;

/**
 *
 * @author mpasc
 */
public final class Keycode
{
    private Keycode() {}
    
    /**
     * The first number in the range of ids used for key events.
     */
    public static final int KEY_FIRST = KeyEvent.KEY_FIRST;

    /**
     * The last number in the range of ids used for key events.
     */
    public static final int KEY_LAST  = KeyEvent.KEY_LAST;

    /**
     * The "key typed" event.  This event is generated when a character is
     * entered.  In the simplest case, it is produced by a single key press.
     * Often, however, characters are produced by series of key presses, and
     * the mapping from key pressed events to key typed events may be
     * many-to-one or many-to-many.
     */
    public static final int KEY_TYPED = KEY_FIRST;

    /**
     * The "key pressed" event. This event is generated when a key
     * is pushed down.
     */
    public static final int KEY_PRESSED = 1 + KEY_FIRST; //Event.KEY_PRESS

    /**
     * The "key released" event. This event is generated when a key
     * is let up.
     */
    public static final int KEY_RELEASED = 2 + KEY_FIRST; //Event.KEY_RELEASE

    /* Virtual key codes. */

    public static final int VK_ENTER          = '\n';
    public static final int VK_BACK_SPACE     = '\b';
    public static final int VK_TAB            = '\t';
    public static final int VK_CANCEL         = 0x03;
    public static final int VK_CLEAR          = 0x0C;
    public static final int VK_SHIFT          = 0x10;
    public static final int VK_CONTROL        = 0x11;
    public static final int VK_ALT            = 0x12;
    public static final int VK_PAUSE          = 0x13;
    public static final int VK_CAPS_LOCK      = 0x14;
    public static final int VK_ESCAPE         = 0x1B;
    public static final int VK_SPACE          = 0x20;
    public static final int VK_PAGE_UP        = 0x21;
    public static final int VK_PAGE_DOWN      = 0x22;
    public static final int VK_END            = 0x23;
    public static final int VK_HOME           = 0x24;

    /**
     * Constant for the non-numpad <b>left</b> arrow key.
     * @see #VK_KP_LEFT
     */
    public static final int VK_LEFT           = 0x25;

    /**
     * Constant for the non-numpad <b>up</b> arrow key.
     * @see #VK_KP_UP
     */
    public static final int VK_UP             = 0x26;

    /**
     * Constant for the non-numpad <b>right</b> arrow key.
     * @see #VK_KP_RIGHT
     */
    public static final int VK_RIGHT          = 0x27;

    /**
     * Constant for the non-numpad <b>down</b> arrow key.
     * @see #VK_KP_DOWN
     */
    public static final int VK_DOWN           = 0x28;

    /**
     * Constant for the comma key, ","
     */
    public static final int VK_COMMA          = 0x2C;

    /**
     * Constant for the minus key, "-"
     * @since 1.2
     */
    public static final int VK_MINUS          = 0x2D;

    /**
     * Constant for the period key, "."
     */
    public static final int VK_PERIOD         = 0x2E;

    /**
     * Constant for the forward slash key, "/"
     */
    public static final int VK_SLASH          = 0x2F;

    /** VK_0 thru VK_9 are the same as ASCII '0' thru '9' (0x30 - 0x39) */
    public static final int VK_0              = 0x30;
    public static final int VK_1              = 0x31;
    public static final int VK_2              = 0x32;
    public static final int VK_3              = 0x33;
    public static final int VK_4              = 0x34;
    public static final int VK_5              = 0x35;
    public static final int VK_6              = 0x36;
    public static final int VK_7              = 0x37;
    public static final int VK_8              = 0x38;
    public static final int VK_9              = 0x39;

    /**
     * Constant for the semicolon key, ";"
     */
    public static final int VK_SEMICOLON      = 0x3B;

    /**
     * Constant for the equals key, "="
     */
    public static final int VK_EQUALS         = 0x3D;

    /** VK_A thru VK_Z are the same as ASCII 'A' thru 'Z' (0x41 - 0x5A) */
    public static final int VK_A              = 0x41;
    public static final int VK_B              = 0x42;
    public static final int VK_C              = 0x43;
    public static final int VK_D              = 0x44;
    public static final int VK_E              = 0x45;
    public static final int VK_F              = 0x46;
    public static final int VK_G              = 0x47;
    public static final int VK_H              = 0x48;
    public static final int VK_I              = 0x49;
    public static final int VK_J              = 0x4A;
    public static final int VK_K              = 0x4B;
    public static final int VK_L              = 0x4C;
    public static final int VK_M              = 0x4D;
    public static final int VK_N              = 0x4E;
    public static final int VK_O              = 0x4F;
    public static final int VK_P              = 0x50;
    public static final int VK_Q              = 0x51;
    public static final int VK_R              = 0x52;
    public static final int VK_S              = 0x53;
    public static final int VK_T              = 0x54;
    public static final int VK_U              = 0x55;
    public static final int VK_V              = 0x56;
    public static final int VK_W              = 0x57;
    public static final int VK_X              = 0x58;
    public static final int VK_Y              = 0x59;
    public static final int VK_Z              = 0x5A;

    /**
     * Constant for the open bracket key, "["
     */
    public static final int VK_OPEN_BRACKET   = 0x5B;

    /**
     * Constant for the back slash key, "\"
     */
    public static final int VK_BACK_SLASH     = 0x5C;

    /**
     * Constant for the close bracket key, "]"
     */
    public static final int VK_CLOSE_BRACKET  = 0x5D;

    public static final int VK_NUMPAD0        = 0x60;
    public static final int VK_NUMPAD1        = 0x61;
    public static final int VK_NUMPAD2        = 0x62;
    public static final int VK_NUMPAD3        = 0x63;
    public static final int VK_NUMPAD4        = 0x64;
    public static final int VK_NUMPAD5        = 0x65;
    public static final int VK_NUMPAD6        = 0x66;
    public static final int VK_NUMPAD7        = 0x67;
    public static final int VK_NUMPAD8        = 0x68;
    public static final int VK_NUMPAD9        = 0x69;
    public static final int VK_MULTIPLY       = 0x6A;
    public static final int VK_ADD            = 0x6B;

    /**
     * This constant is obsolete, and is included only for backwards
     * compatibility.
     * @see #VK_SEPARATOR
     */
    public static final int VK_SEPARATER      = 0x6C;

    /**
     * Constant for the Numpad Separator key.
     * @since 1.4
     */
    public static final int VK_SEPARATOR      = VK_SEPARATER;

    public static final int VK_SUBTRACT       = 0x6D;
    public static final int VK_DECIMAL        = 0x6E;
    public static final int VK_DIVIDE         = 0x6F;
    public static final int VK_DELETE         = 0x7F; /* ASCII DEL */
    public static final int VK_NUM_LOCK       = 0x90;
    public static final int VK_SCROLL_LOCK    = 0x91;

    /** Constant for the F1 function key. */
    public static final int VK_F1             = 0x70;

    /** Constant for the F2 function key. */
    public static final int VK_F2             = 0x71;

    /** Constant for the F3 function key. */
    public static final int VK_F3             = 0x72;

    /** Constant for the F4 function key. */
    public static final int VK_F4             = 0x73;

    /** Constant for the F5 function key. */
    public static final int VK_F5             = 0x74;

    /** Constant for the F6 function key. */
    public static final int VK_F6             = 0x75;

    /** Constant for the F7 function key. */
    public static final int VK_F7             = 0x76;

    /** Constant for the F8 function key. */
    public static final int VK_F8             = 0x77;

    /** Constant for the F9 function key. */
    public static final int VK_F9             = 0x78;

    /** Constant for the F10 function key. */
    public static final int VK_F10            = 0x79;

    /** Constant for the F11 function key. */
    public static final int VK_F11            = 0x7A;

    /** Constant for the F12 function key. */
    public static final int VK_F12            = 0x7B;

    /**
     * Constant for the F13 function key.
     * @since 1.2
     */
    /* F13 - F24 are used on IBM 3270 keyboard; use random range for constants. */
    public static final int VK_F13            = 0xF000;

    /**
     * Constant for the F14 function key.
     * @since 1.2
     */
    public static final int VK_F14            = 0xF001;

    /**
     * Constant for the F15 function key.
     * @since 1.2
     */
    public static final int VK_F15            = 0xF002;

    /**
     * Constant for the F16 function key.
     * @since 1.2
     */
    public static final int VK_F16            = 0xF003;

    /**
     * Constant for the F17 function key.
     * @since 1.2
     */
    public static final int VK_F17            = 0xF004;

    /**
     * Constant for the F18 function key.
     * @since 1.2
     */
    public static final int VK_F18            = 0xF005;

    /**
     * Constant for the F19 function key.
     * @since 1.2
     */
    public static final int VK_F19            = 0xF006;

    /**
     * Constant for the F20 function key.
     * @since 1.2
     */
    public static final int VK_F20            = 0xF007;

    /**
     * Constant for the F21 function key.
     * @since 1.2
     */
    public static final int VK_F21            = 0xF008;

    /**
     * Constant for the F22 function key.
     * @since 1.2
     */
    public static final int VK_F22            = 0xF009;

    /**
     * Constant for the F23 function key.
     * @since 1.2
     */
    public static final int VK_F23            = 0xF00A;

    /**
     * Constant for the F24 function key.
     * @since 1.2
     */
    public static final int VK_F24            = 0xF00B;

    public static final int VK_PRINTSCREEN    = 0x9A;
    public static final int VK_INSERT         = 0x9B;
    public static final int VK_HELP           = 0x9C;
    public static final int VK_META           = 0x9D;

    public static final int VK_BACK_QUOTE     = 0xC0;
    public static final int VK_QUOTE          = 0xDE;

    /**
     * Constant for the numeric keypad <b>up</b> arrow key.
     * @see #VK_UP
     * @since 1.2
     */
    public static final int VK_KP_UP          = 0xE0;

    /**
     * Constant for the numeric keypad <b>down</b> arrow key.
     * @see #VK_DOWN
     * @since 1.2
     */
    public static final int VK_KP_DOWN        = 0xE1;

    /**
     * Constant for the numeric keypad <b>left</b> arrow key.
     * @see #VK_LEFT
     * @since 1.2
     */
    public static final int VK_KP_LEFT        = 0xE2;

    /**
     * Constant for the numeric keypad <b>right</b> arrow key.
     * @see #VK_RIGHT
     * @since 1.2
     */
    public static final int VK_KP_RIGHT       = 0xE3;

    /* For European keyboards */
    /** @since 1.2 */
    public static final int VK_DEAD_GRAVE               = 0x80;
    /** @since 1.2 */
    public static final int VK_DEAD_ACUTE               = 0x81;
    /** @since 1.2 */
    public static final int VK_DEAD_CIRCUMFLEX          = 0x82;
    /** @since 1.2 */
    public static final int VK_DEAD_TILDE               = 0x83;
    /** @since 1.2 */
    public static final int VK_DEAD_MACRON              = 0x84;
    /** @since 1.2 */
    public static final int VK_DEAD_BREVE               = 0x85;
    /** @since 1.2 */
    public static final int VK_DEAD_ABOVEDOT            = 0x86;
    /** @since 1.2 */
    public static final int VK_DEAD_DIAERESIS           = 0x87;
    /** @since 1.2 */
    public static final int VK_DEAD_ABOVERING           = 0x88;
    /** @since 1.2 */
    public static final int VK_DEAD_DOUBLEACUTE         = 0x89;
    /** @since 1.2 */
    public static final int VK_DEAD_CARON               = 0x8a;
    /** @since 1.2 */
    public static final int VK_DEAD_CEDILLA             = 0x8b;
    /** @since 1.2 */
    public static final int VK_DEAD_OGONEK              = 0x8c;
    /** @since 1.2 */
    public static final int VK_DEAD_IOTA                = 0x8d;
    /** @since 1.2 */
    public static final int VK_DEAD_VOICED_SOUND        = 0x8e;
    /** @since 1.2 */
    public static final int VK_DEAD_SEMIVOICED_SOUND    = 0x8f;

    /** @since 1.2 */
    public static final int VK_AMPERSAND                = 0x96;
    /** @since 1.2 */
    public static final int VK_ASTERISK                 = 0x97;
    /** @since 1.2 */
    public static final int VK_QUOTEDBL                 = 0x98;
    /** @since 1.2 */
    public static final int VK_LESS                     = 0x99;

    /** @since 1.2 */
    public static final int VK_GREATER                  = 0xa0;
    /** @since 1.2 */
    public static final int VK_BRACELEFT                = 0xa1;
    /** @since 1.2 */
    public static final int VK_BRACERIGHT               = 0xa2;

    /**
     * Constant for the "@" key.
     * @since 1.2
     */
    public static final int VK_AT                       = 0x0200;

    /**
     * Constant for the ":" key.
     * @since 1.2
     */
    public static final int VK_COLON                    = 0x0201;

    /**
     * Constant for the "^" key.
     * @since 1.2
     */
    public static final int VK_CIRCUMFLEX               = 0x0202;

    /**
     * Constant for the "$" key.
     * @since 1.2
     */
    public static final int VK_DOLLAR                   = 0x0203;

    /**
     * Constant for the Euro currency sign key.
     * @since 1.2
     */
    public static final int VK_EURO_SIGN                = 0x0204;

    /**
     * Constant for the "!" key.
     * @since 1.2
     */
    public static final int VK_EXCLAMATION_MARK         = 0x0205;

    /**
     * Constant for the inverted exclamation mark key.
     * @since 1.2
     */
    public static final int VK_INVERTED_EXCLAMATION_MARK = 0x0206;

    /**
     * Constant for the "(" key.
     * @since 1.2
     */
    public static final int VK_LEFT_PARENTHESIS         = 0x0207;

    /**
     * Constant for the "#" key.
     * @since 1.2
     */
    public static final int VK_NUMBER_SIGN              = 0x0208;

    /**
     * Constant for the "+" key.
     * @since 1.2
     */
    public static final int VK_PLUS                     = 0x0209;

    /**
     * Constant for the ")" key.
     * @since 1.2
     */
    public static final int VK_RIGHT_PARENTHESIS        = 0x020A;

    /**
     * Constant for the "_" key.
     * @since 1.2
     */
    public static final int VK_UNDERSCORE               = 0x020B;

    /**
     * Constant for the Microsoft Windows "Windows" key.
     * It is used for both the left and right version of the key.
     * @see #getKeyLocation()
     * @since 1.5
     */
    public static final int VK_WINDOWS                  = 0x020C;

    /**
     * Constant for the Microsoft Windows Context Menu key.
     * @since 1.5
     */
    public static final int VK_CONTEXT_MENU             = 0x020D;

    /* for input method support on Asian Keyboards */

    /* not clear what this means - listed in Microsoft Windows API */
    public static final int VK_FINAL                    = 0x0018;

    /** Constant for the Convert function key. */
    /* Japanese PC 106 keyboard, Japanese Solaris keyboard: henkan */
    public static final int VK_CONVERT                  = 0x001C;

    /** Constant for the Don't Convert function key. */
    /* Japanese PC 106 keyboard: muhenkan */
    public static final int VK_NONCONVERT               = 0x001D;

    /** Constant for the Accept or Commit function key. */
    /* Japanese Solaris keyboard: kakutei */
    public static final int VK_ACCEPT                   = 0x001E;

    /* not clear what this means - listed in Microsoft Windows API */
    public static final int VK_MODECHANGE               = 0x001F;

    /* replaced by VK_KANA_LOCK for Microsoft Windows and Solaris;
       might still be used on other platforms */
    public static final int VK_KANA                     = 0x0015;

    /* replaced by VK_INPUT_METHOD_ON_OFF for Microsoft Windows and Solaris;
       might still be used for other platforms */
    public static final int VK_KANJI                    = 0x0019;

    /**
     * Constant for the Alphanumeric function key.
     * @since 1.2
     */
    /* Japanese PC 106 keyboard: eisuu */
    public static final int VK_ALPHANUMERIC             = 0x00F0;

    /**
     * Constant for the Katakana function key.
     * @since 1.2
     */
    /* Japanese PC 106 keyboard: katakana */
    public static final int VK_KATAKANA                 = 0x00F1;

    /**
     * Constant for the Hiragana function key.
     * @since 1.2
     */
    /* Japanese PC 106 keyboard: hiragana */
    public static final int VK_HIRAGANA                 = 0x00F2;

    /**
     * Constant for the Full-Width Characters function key.
     * @since 1.2
     */
    /* Japanese PC 106 keyboard: zenkaku */
    public static final int VK_FULL_WIDTH               = 0x00F3;

    /**
     * Constant for the Half-Width Characters function key.
     * @since 1.2
     */
    /* Japanese PC 106 keyboard: hankaku */
    public static final int VK_HALF_WIDTH               = 0x00F4;

    /**
     * Constant for the Roman Characters function key.
     * @since 1.2
     */
    /* Japanese PC 106 keyboard: roumaji */
    public static final int VK_ROMAN_CHARACTERS         = 0x00F5;

    /**
     * Constant for the All Candidates function key.
     * @since 1.2
     */
    /* Japanese PC 106 keyboard - VK_CONVERT + ALT: zenkouho */
    public static final int VK_ALL_CANDIDATES           = 0x0100;

    /**
     * Constant for the Previous Candidate function key.
     * @since 1.2
     */
    /* Japanese PC 106 keyboard - VK_CONVERT + SHIFT: maekouho */
    public static final int VK_PREVIOUS_CANDIDATE       = 0x0101;

    /**
     * Constant for the Code Input function key.
     * @since 1.2
     */
    /* Japanese PC 106 keyboard - VK_ALPHANUMERIC + ALT: kanji bangou */
    public static final int VK_CODE_INPUT               = 0x0102;

    /**
     * Constant for the Japanese-Katakana function key.
     * This key switches to a Japanese input method and selects its Katakana input mode.
     * @since 1.2
     */
    /* Japanese Macintosh keyboard - VK_JAPANESE_HIRAGANA + SHIFT */
    public static final int VK_JAPANESE_KATAKANA        = 0x0103;

    /**
     * Constant for the Japanese-Hiragana function key.
     * This key switches to a Japanese input method and selects its Hiragana input mode.
     * @since 1.2
     */
    /* Japanese Macintosh keyboard */
    public static final int VK_JAPANESE_HIRAGANA        = 0x0104;

    /**
     * Constant for the Japanese-Roman function key.
     * This key switches to a Japanese input method and selects its Roman-Direct input mode.
     * @since 1.2
     */
    /* Japanese Macintosh keyboard */
    public static final int VK_JAPANESE_ROMAN           = 0x0105;

    /**
     * Constant for the locking Kana function key.
     * This key locks the keyboard into a Kana layout.
     * @since 1.3
     */
    /* Japanese PC 106 keyboard with special Windows driver - eisuu + Control; Japanese Solaris keyboard: kana */
    public static final int VK_KANA_LOCK                = 0x0106;

    /**
     * Constant for the input method on/off key.
     * @since 1.3
     */
    /* Japanese PC 106 keyboard: kanji. Japanese Solaris keyboard: nihongo */
    public static final int VK_INPUT_METHOD_ON_OFF      = 0x0107;

    /* for Sun keyboards */
    /** @since 1.2 */
    public static final int VK_CUT                      = 0xFFD1;
    /** @since 1.2 */
    public static final int VK_COPY                     = 0xFFCD;
    /** @since 1.2 */
    public static final int VK_PASTE                    = 0xFFCF;
    /** @since 1.2 */
    public static final int VK_UNDO                     = 0xFFCB;
    /** @since 1.2 */
    public static final int VK_AGAIN                    = 0xFFC9;
    /** @since 1.2 */
    public static final int VK_FIND                     = 0xFFD0;
    /** @since 1.2 */
    public static final int VK_PROPS                    = 0xFFCA;
    /** @since 1.2 */
    public static final int VK_STOP                     = 0xFFC8;

    /**
     * Constant for the Compose function key.
     * @since 1.2
     */
    public static final int VK_COMPOSE                  = 0xFF20;

    /**
     * Constant for the AltGraph function key.
     * @since 1.2
     */
    public static final int VK_ALT_GRAPH                = 0xFF7E;

    /**
     * Constant for the Begin key.
     * @since 1.5
     */
    public static final int VK_BEGIN                    = 0xFF58;

    /**
     * This value is used to indicate that the keyCode is unknown.
     * KEY_TYPED events do not have a keyCode value; this value
     * is used instead.
     */
    public static final int VK_UNDEFINED      = 0x0;
    
    public static final int VK_SHIFT_LEFT          = 0xFC;
    public static final int VK_SHIFT_RIGHT          = 0xFD;
    public static final int VK_CONTROL_LEFT        = 0xFE;
    public static final int VK_CONTROL_RIGHT        = 0xFF;

    /**
     * KEY_PRESSED and KEY_RELEASED events which do not map to a
     * valid Unicode character use this for the keyChar value.
     */
    public static final char CHAR_UNDEFINED   = 0xFFFF;

    /**
     * A constant indicating that the keyLocation is indeterminate
     * or not relevant.
     * <code>KEY_TYPED</code> events do not have a keyLocation; this value
     * is used instead.
     * @since 1.4
     */
    public static final int KEY_LOCATION_UNKNOWN  = 0;

    /**
     * A constant indicating that the key pressed or released
     * is not distinguished as the left or right version of a key,
     * and did not originate on the numeric keypad (or did not
     * originate with a virtual key corresponding to the numeric
     * keypad).
     * @since 1.4
     */
    public static final int KEY_LOCATION_STANDARD = 1;

    /**
     * A constant indicating that the key pressed or released is in
     * the left key location (there is more than one possible location
     * for this key).  Example: the left shift key.
     * @since 1.4
     */
    public static final int KEY_LOCATION_LEFT     = 2;

    /**
     * A constant indicating that the key pressed or released is in
     * the right key location (there is more than one possible location
     * for this key).  Example: the right shift key.
     * @since 1.4
     */
    public static final int KEY_LOCATION_RIGHT    = 3;

    /**
     * A constant indicating that the key event originated on the
     * numeric keypad or with a virtual key corresponding to the
     * numeric keypad.
     * @since 1.4
     */
    public static final int KEY_LOCATION_NUMPAD   = 4;
    
    
    
    public static final String getKeyName(InputId id)
    {
        if(!(id instanceof KeyId))
            return null;
        return getKeyName(((KeyId)id).getCode());
    }
    public static final String getKeyName(int keycode)
    {
        switch(keycode)
        {
            case Keycode.VK_ENTER: return "ENTER";
            case Keycode.VK_BACK_SPACE: return "BACK_SPACE";
            case Keycode.VK_TAB: return "TAB";
            case Keycode.VK_CANCEL: return "CANCEL";
            case Keycode.VK_CLEAR: return "CLEAR";
            case Keycode.VK_SHIFT: return "SHIFT";
            case Keycode.VK_CONTROL: return "CONTROL";
            case Keycode.VK_ALT: return "ALT";
            case Keycode.VK_PAUSE: return "PAUSE";
            case Keycode.VK_CAPS_LOCK: return "CAPS_LOCK";
            case Keycode.VK_ESCAPE: return "ESCAPE";
            case Keycode.VK_SPACE: return "SPACE";
            case Keycode.VK_PAGE_UP: return "PAGE_UP";
            case Keycode.VK_PAGE_DOWN: return "PAGE_DOWN";
            case Keycode.VK_END: return "END";
            case Keycode.VK_HOME: return "HOME";
            case Keycode.VK_LEFT: return "LEFT";
            case Keycode.VK_UP: return "UP";
            case Keycode.VK_RIGHT: return "RIGHT";
            case Keycode.VK_DOWN: return "DOWN";
            case Keycode.VK_COMMA: return "COMMA";
            case Keycode.VK_MINUS: return "MINUS";
            case Keycode.VK_PERIOD: return "PERIOD";
            case Keycode.VK_SLASH: return "SLASH";
            case Keycode.VK_0: return "0";
            case Keycode.VK_1: return "1";
            case Keycode.VK_2: return "2";
            case Keycode.VK_3: return "3";
            case Keycode.VK_4: return "4";
            case Keycode.VK_5: return "5";
            case Keycode.VK_6: return "6";
            case Keycode.VK_7: return "7";
            case Keycode.VK_8: return "8";
            case Keycode.VK_9: return "9";
            case Keycode.VK_SEMICOLON: return "SEMICOLON";
            case Keycode.VK_EQUALS: return "EQUALS";
            case Keycode.VK_A: return "A";
            case Keycode.VK_B: return "B";
            case Keycode.VK_C: return "C";
            case Keycode.VK_D: return "D";
            case Keycode.VK_E: return "E";
            case Keycode.VK_F: return "F";
            case Keycode.VK_G: return "G";
            case Keycode.VK_H: return "H";
            case Keycode.VK_I: return "I";
            case Keycode.VK_J: return "J";
            case Keycode.VK_K: return "K";
            case Keycode.VK_L: return "L";
            case Keycode.VK_M: return "M";
            case Keycode.VK_N: return "N";
            case Keycode.VK_O: return "O";
            case Keycode.VK_P: return "P";
            case Keycode.VK_Q: return "Q";
            case Keycode.VK_R: return "R";
            case Keycode.VK_S: return "S";
            case Keycode.VK_T: return "T";
            case Keycode.VK_U: return "U";
            case Keycode.VK_V: return "V";
            case Keycode.VK_W: return "W";
            case Keycode.VK_X: return "X";
            case Keycode.VK_Y: return "Y";
            case Keycode.VK_Z: return "Z";
            case Keycode.VK_OPEN_BRACKET: return "OPEN_BRACKET";
            case Keycode.VK_BACK_SLASH: return "BACK_SLASH";
            case Keycode.VK_CLOSE_BRACKET: return "CLOSE_BRACKET";
            case Keycode.VK_NUMPAD0: return "NUMPAD0";
            case Keycode.VK_NUMPAD1: return "NUMPAD1";
            case Keycode.VK_NUMPAD2: return "NUMPAD2";
            case Keycode.VK_NUMPAD3: return "NUMPAD3";
            case Keycode.VK_NUMPAD4: return "NUMPAD4";
            case Keycode.VK_NUMPAD5: return "NUMPAD5";
            case Keycode.VK_NUMPAD6: return "NUMPAD6";
            case Keycode.VK_NUMPAD7: return "NUMPAD7";
            case Keycode.VK_NUMPAD8: return "NUMPAD8";
            case Keycode.VK_NUMPAD9: return "NUMPAD9";
            case Keycode.VK_MULTIPLY: return "MULTIPLY";
            case Keycode.VK_ADD: return "ADD";
            case Keycode.VK_SEPARATOR: return "SEPARATOR";
            case Keycode.VK_SUBTRACT: return "SUBTRACT";
            case Keycode.VK_DECIMAL: return "DECIMAL";
            case Keycode.VK_DIVIDE: return "DIVIDE";
            case Keycode.VK_NUM_LOCK: return "NUM_LOCK";
            case Keycode.VK_SCROLL_LOCK: return "SCROLL_LOCK";
            case Keycode.VK_F1: return "F1";
            case Keycode.VK_F2: return "F2";
            case Keycode.VK_F3: return "F3";
            case Keycode.VK_F4: return "F4";
            case Keycode.VK_F5: return "F5";
            case Keycode.VK_F6: return "F6";
            case Keycode.VK_F7: return "F7";
            case Keycode.VK_F8: return "F8";
            case Keycode.VK_F9: return "F9";
            case Keycode.VK_F10: return "F10";
            case Keycode.VK_F11: return "F11";
            case Keycode.VK_F12: return "F12";
            case Keycode.VK_F13: return "F13";
            case Keycode.VK_F14: return "F14";
            case Keycode.VK_F15: return "F15";
            case Keycode.VK_F16: return "F16";
            case Keycode.VK_F17: return "F17";
            case Keycode.VK_F18: return "F18";
            case Keycode.VK_F19: return "F19";
            case Keycode.VK_F20: return "F20";
            case Keycode.VK_F21: return "F21";
            case Keycode.VK_F22: return "F22";
            case Keycode.VK_F23: return "F23";
            case Keycode.VK_F24: return "F24";
            case Keycode.VK_PRINTSCREEN: return "PRINTSCREEN";
            case Keycode.VK_INSERT: return "INSERT";
            case Keycode.VK_HELP: return "HELP";
            case Keycode.VK_META: return "META";
            case Keycode.VK_BACK_QUOTE: return "BACK_QUOTE";
            case Keycode.VK_QUOTE: return "QUOTE";
            case Keycode.VK_KP_UP: return "KP_UP";
            case Keycode.VK_KP_DOWN: return "KP_DOWN";
            case Keycode.VK_KP_LEFT: return "KP_LEFT";
            case Keycode.VK_KP_RIGHT: return "KP_RIGHT";
            case Keycode.VK_DEAD_GRAVE: return "DEAD_GRAVE";
            case Keycode.VK_DEAD_ACUTE: return "DEAD_ACUTE";
            case Keycode.VK_DEAD_CIRCUMFLEX: return "DEAD_CIRCUMFLEX";
            case Keycode.VK_DEAD_TILDE: return "DEAD_TILDE";
            case Keycode.VK_DEAD_MACRON: return "DEAD_MACRON";
            case Keycode.VK_DEAD_BREVE: return "DEAD_BREVE";
            case Keycode.VK_DEAD_ABOVEDOT: return "DEAD_ABOVEDOT";
            case Keycode.VK_DEAD_DIAERESIS: return "DEAD_DIAERESIS";
            case Keycode.VK_DEAD_ABOVERING: return "DEAD_ABOVERING";
            case Keycode.VK_DEAD_DOUBLEACUTE: return "DEAD_DOUBLEACUTE";
            case Keycode.VK_DEAD_CARON: return "DEAD_CARON";
            case Keycode.VK_DEAD_CEDILLA: return "DEAD_CEDILLA";
            case Keycode.VK_DEAD_OGONEK: return "DEAD_OGONEK";
            case Keycode.VK_DEAD_IOTA: return "DEAD_IOTA";
            case Keycode.VK_DEAD_VOICED_SOUND: return "DEAD_VOICED_SOUND";
            case Keycode.VK_DEAD_SEMIVOICED_SOUND: return "DEAD_SEMIVOICED_SOUND";
            case Keycode.VK_AMPERSAND: return "AMPERSAND";
            case Keycode.VK_ASTERISK: return "ASTERISK";
            case Keycode.VK_QUOTEDBL: return "QUOTEDBL";
            case Keycode.VK_LESS: return "LESS";
            case Keycode.VK_GREATER: return "GREATER";
            case Keycode.VK_BRACELEFT: return "BRACELEFT";
            case Keycode.VK_BRACERIGHT: return "BRACERIGHT";
            case Keycode.VK_AT: return "AT";
            case Keycode.VK_COLON: return "COLON";
            case Keycode.VK_CIRCUMFLEX: return "CIRCUMFLEX";
            case Keycode.VK_DOLLAR: return "DOLLAR";
            case Keycode.VK_EURO_SIGN: return "EURO_SIGN";
            case Keycode.VK_EXCLAMATION_MARK: return "EXCLAMATION_MARK";
            case Keycode.VK_INVERTED_EXCLAMATION_MARK: return "INVERTED_EXCLAMATION_MARK";
            case Keycode.VK_LEFT_PARENTHESIS: return "LEFT_PARENTHESIS";
            case Keycode.VK_NUMBER_SIGN: return "NUMBER_SIGN";
            case Keycode.VK_PLUS: return "PLUS";
            case Keycode.VK_RIGHT_PARENTHESIS: return "RIGHT_PARENTHESIS";
            case Keycode.VK_UNDERSCORE: return "UNDERSCORE";
            case Keycode.VK_WINDOWS: return "WINDOWS";
            case Keycode.VK_CONTEXT_MENU: return "CONTEXT_MENU";
            case Keycode.VK_FINAL: return "FINAL";
            case Keycode.VK_CONVERT: return "CONVERT";
            case Keycode.VK_NONCONVERT: return "NONCONVERT";
            case Keycode.VK_ACCEPT: return "ACCEPT";
            case Keycode.VK_MODECHANGE: return "MODECHANGE";
            case Keycode.VK_KANA: return "KANA";
            case Keycode.VK_KANJI: return "KANJI";
            case Keycode.VK_ALPHANUMERIC: return "ALPHANUMERIC";
            case Keycode.VK_KATAKANA: return "KATAKANA";
            case Keycode.VK_HIRAGANA: return "HIRAGANA";
            case Keycode.VK_FULL_WIDTH: return "FULL_WIDTH";
            case Keycode.VK_HALF_WIDTH: return "HALF_WIDTH";
            case Keycode.VK_ROMAN_CHARACTERS: return "ROMAN_CHARACTERS";
            case Keycode.VK_ALL_CANDIDATES: return "ALL_CANDIDATES";
            case Keycode.VK_PREVIOUS_CANDIDATE: return "PREVIOUS_CANDIDATE";
            case Keycode.VK_CODE_INPUT: return "CODE_INPUT";
            case Keycode.VK_JAPANESE_KATAKANA: return "JAPANESE_KATAKANA";
            case Keycode.VK_JAPANESE_HIRAGANA: return "JAPANESE_HIRAGANA";
            case Keycode.VK_JAPANESE_ROMAN: return "JAPANESE_ROMAN";
            case Keycode.VK_KANA_LOCK: return "KANA_LOCK";
            case Keycode.VK_INPUT_METHOD_ON_OFF: return "INPUT_METHOD_ON_OFF";
            case Keycode.VK_CUT: return "CUT";
            case Keycode.VK_COPY: return "COPY";
            case Keycode.VK_PASTE: return "PASTE";
            case Keycode.VK_UNDO: return "UNDO";
            case Keycode.VK_AGAIN: return "AGAIN";
            case Keycode.VK_FIND: return "FIND";
            case Keycode.VK_PROPS: return "PROPS";
            case Keycode.VK_STOP: return "STOP";
            case Keycode.VK_COMPOSE: return "COMPOSE";
            case Keycode.VK_ALT_GRAPH: return "ALT_GRAPH";
            case Keycode.VK_BEGIN: return "BEGIN";
            case Keycode.VK_UNDEFINED: return "UNDEFINED";
            case Keycode.VK_SHIFT_LEFT: return "SHIFT_LEFT";
            case Keycode.VK_SHIFT_RIGHT: return "SHIFT_RIGHT";
            case Keycode.VK_CONTROL_LEFT: return "CONTROL_LEFT";
            case Keycode.VK_CONTROL_RIGHT: return "CONTROL_RIGHT";
            default: return null;
        }
    }
}
