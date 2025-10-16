# DART Editor (Processing) — README

**Version:** v1.0 (example)  
**Project site:** https://dartmobo.com/dart_diy/dart_editor/  
**Platform:** Windows 64‑bit 32-bit
**Runtime:** Oracle Java SE 8u202 (bundled, unmodified)

## What it is
DART Editor is a companion app for DARTMOBO‑based controllers. It lets you **create, modify, upload, and save presets** for your hardware. It also acts as a **MIDI Monitor** and can be adapted to **custom layouts**, so the on‑screen UI mirrors a DIY controller. The editor can even work as a **software MIDI controller** when needed.

## Key features
- **Preset authoring**: edit and upload settings to your DART controller (Send All / Send This).
- **MIDI Monitor**: incoming/outgoing MIDI with filters, raw bytes panel, and keystroke monitor.
- **Customizable layout (Draw Mode)**: add/move/resize items (up to 60), assign names, hints, masks.
- **Two Pages per preset**: Page‑1 and Page‑2 settings per item.
- **CSV presets**: human‑readable `.csv` files for load/save and a customizable `Default-Preset.csv`.
- **Fullscreen Items Area**: toggle with keyboard (see Shortcuts).

## System requirements
- Windows 10/11 64‑bit 32-bit
- MIDI drivers (class‑compliant) for your controller
- No separate Java installation required (JRE 8u202 is included)

## Quick start
1. Unzip the package to a writable folder.
2. Run `DART_Editor.exe`.
3. On first launch, **select MIDI IN / MIDI OUT** ports used for communication. Typical names include **“DART”** or **“Arduino Leonardo”**.
4. Start editing, then **Send All** (entire preset) or **Send This** (only the selected Item). During a successful upload, all controller LEDs flash intermittently.
5. Use **Load / Save** to work with `.csv` presets. You can customize the **`data/Default-Preset.csv`** that opens at startup.

## UI overview
1. **Items Area** — up to **60 Items** representing modifiers (pots, spinners, buttons, sensors, etc.). You can vary **shape**, **size**, **position**, and **name**.
2. **MIDI Monitor** — four‑parameter view (Channel, Type, Data1, Data2). Includes **filters**, a **Raw‑Data** panel (status/data1/data2), and a **Keystrokes** panel for HID emulation. Incoming messages highlight the corresponding Item.
3. **Send All / Send This** — upload the whole preset or only the selected Item/page.
4. **Settings Box** — all selectors for the current Item (the main selector is **MODE**; others update based on MODE).
5. **Pages** — each preset has **two pages**; an Item may have different settings per page.
6. **Load / Save** — presets are `.csv` files and can be edited with a text editor.
7. **Hint Box** — on‑screen contextual help for hovered/selected elements.
8. **Draw Mode (Edit)** — add/delete/shape Items in the layout area.

## Modes (examples) for items
- **BUTTON / TOGGLE / GROUPS / RADIO**: debounced on‑off behaviors, optional LED assignment.
- **POT / HYPERCURVE / CENTER‑CURVE**: continuous outputs with different response curves.
- **ENCODER / SPINNER / TOUCH / PADS / PIEZO**: specialized inputs and behaviors.
- **PAGE SWITCH / GENERAL / MOUSE‑ARROWS / USER**: global controls and special functions.

> Tip: Some modes link their settings between Page‑1 and Page‑2 (e.g., **PAGE SWITCH**).

## Draw Mode (Edit) — essentials
- **Move**: Shift + Left‑drag  
- **Resize**: Shift + Mouse Wheel  
- **Shape**: Ctrl + Mouse Wheel  
- **Width**: `W` + Mouse Wheel  
- **Height**: `H` + Mouse Wheel  
- **Mask**: `M` + Wheel (type), `N` + Wheel (size), `B` + Wheel (layer)  
- **Hide current**: Ctrl + `-` or Ctrl + `8` (hidden items become **BLIND INPUT**)  
- **Hide all**: Ctrl + `0` (items become **BLIND INPUT**)  
- **Add item**: Ctrl + `+` or Ctrl + `9` (up to 60 Items)

**Settings visible only in Draw Mode:**
- **Memory Position** — map an Item to the controller’s memory slot (matches the physical circuit position).
- **Hint** — custom help text shown in the Hint Box.
- **Name** — label shown in the Items Area.

## Shortcuts
- **Fullscreen Items Area**: press **`G`** and **`F`** on the keyboard.
- **Monitor toggles**: **IN / OUT** filters to exclude streams from the monitor.

## Files & folders
- `data/Default-Preset.csv` — opens on startup; customize for your controller.
-  Specific presets for various Dart controllers available on the website

## Troubleshooting
- **No MIDI ports listed**: connect the controller and restart the app; verify drivers.
- **No sound feedback** (buzzer): check system audio output and app focus.
- **Upload fails**: ensure correct ports (IN/OUT) and try **Send This** on one Item first.

## Privacy
The app does **not** collect telemetry or send personal data.

## License & notices
- App: © Massimiliano Marchese. All rights reserved. Free to use; redistribution/modification terms in `LICENSE.txt`.
- Bundled runtime: see `licenses/ORACLE_JRE_8u202.txt`.
- Third‑party libraries used at runtime (not bundled): see `licenses/THIRD_PARTY_NOTICES.txt`.

## Credits
- Author: **Massimiliano Marchese**  
- Contributor: Piero Pappalardo

---
This README is a concise user‑facing summary. For deeper technical details (modes, curves, HID, DMX, etc.), refer to the project page and in‑app hints.
