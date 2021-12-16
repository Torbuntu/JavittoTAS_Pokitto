// Generated File - DO NOT EDIT
#pragma once

enum MapEnum {
	EMPTY = 0,
	Collide = 1 << 0,
	WalkOnGrass = 1 << 1,
	GoToTitle = 1 << 2
};



inline const uint8_t tiles[] = {
// 0: 2:1,1:437
0x89,0x88,0x88,0x88,0x88,0x89,0x98,0x98,
0x99,0x88,0x88,0x98,0x98,0x88,0x88,0x88,
0x98,0x88,0x99,0x99,0x99,0x88,0x88,0x89,
0x88,0x88,0x89,0x99,0x98,0x88,0x89,0x88,
0x88,0x88,0x98,0x99,0x89,0x88,0x88,0x89,
0x88,0x88,0x88,0x98,0x88,0x88,0x88,0x89,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x98,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x98,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x88,0x88,0x88,0x88,0x88,0x88,0x89,0x98,
0x89,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x89,0x99,0x98,0x8c,0x89,0x99,0x99,0x99,
0x99,0x99,0x98,0xcc,0x89,0x99,0x99,0x98,
0x89,0x99,0x8c,0xcc,0x88,0x99,0x99,0x99,
0x89,0x99,0x9c,0x8c,0xc9,0x99,0x98,0x88,
0x88,0x89,0x99,0x99,0x99,0x99,0x98,0x98,
,

// 1: 2:1
0x89,0x88,0x88,0x98,0x88,0x89,0x98,0x98,
0x99,0x98,0x88,0x98,0x98,0x88,0x88,0x88,
0x98,0x98,0x98,0x99,0x98,0x89,0x88,0x89,
0x89,0x88,0x89,0x89,0x99,0x89,0x89,0x88,
0x89,0x99,0x89,0x88,0x89,0x99,0x99,0x89,
0x99,0x99,0x98,0x88,0x89,0x99,0x88,0x89,
0x88,0x99,0x89,0x88,0x98,0x89,0x89,0x98,
0x88,0x88,0x89,0x88,0x88,0x89,0x88,0x98,
0x88,0x88,0x89,0x98,0x88,0x88,0x99,0x88,
0x89,0x88,0x98,0x88,0x98,0x98,0x89,0x98,
0x89,0x88,0x88,0x98,0x88,0x89,0x88,0x88,
0x89,0x98,0x99,0x98,0x98,0x99,0x89,0x99,
0x98,0x99,0x88,0x98,0x98,0x98,0x98,0x98,
0x89,0x99,0x99,0x88,0x88,0x88,0x98,0x99,
0x89,0x89,0x99,0x98,0x98,0x98,0x98,0x88,
0x88,0x88,0x98,0x88,0x98,0x99,0x98,0x98,
,

// 2: 2:236,1:437
0x99,0x88,0x88,0x88,0x88,0x89,0x99,0x98,
0x98,0x88,0x88,0x98,0x98,0x88,0x88,0x89,
0x88,0x88,0x99,0x99,0x99,0x88,0x89,0x89,
0x88,0x88,0x89,0x99,0x98,0x88,0x89,0x88,
0x88,0x88,0x98,0x99,0x89,0x88,0x88,0x99,
0x88,0x88,0x88,0x98,0x88,0x88,0x88,0x88,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x98,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x88,0x88,0x88,0x88,0x88,0x88,0x89,0x88,
0x89,0x88,0x88,0x88,0x88,0x88,0x98,0x99,
0x88,0x99,0x98,0x8c,0x89,0x99,0x99,0x98,
0x89,0x99,0x98,0xcc,0x89,0x99,0x99,0x99,
0x89,0x99,0x8c,0xcc,0x88,0x99,0x99,0x98,
0x89,0x99,0x9c,0x8c,0xc9,0x99,0x99,0x98,
0x99,0x99,0x99,0x99,0x99,0x99,0x99,0x99,
,

// 3: 2:236
0x99,0x88,0x98,0x98,0x98,0x89,0x99,0x98,
0x98,0x98,0x88,0x99,0x98,0x88,0x88,0x89,
0x88,0x88,0x99,0x99,0x88,0x99,0x89,0x89,
0x89,0x88,0x89,0x88,0x99,0x99,0x99,0x88,
0x99,0x99,0x88,0x88,0x89,0x99,0x98,0x99,
0x89,0x98,0x88,0x88,0x99,0x88,0x99,0x88,
0x88,0x98,0x89,0x89,0x98,0x99,0x99,0x98,
0x88,0x88,0x89,0x88,0x88,0x88,0x98,0x88,
0x88,0x88,0x88,0x88,0x98,0x88,0x99,0x88,
0x89,0x88,0x98,0x98,0x98,0x99,0x89,0x88,
0x89,0x88,0x98,0x98,0x88,0x89,0x98,0x99,
0x88,0x98,0x99,0x99,0x99,0x99,0x99,0x98,
0x88,0x99,0x88,0x88,0x88,0x98,0x88,0x99,
0x89,0x99,0x99,0x98,0x98,0x88,0x98,0x98,
0x89,0x91,0x99,0x11,0x99,0x98,0x99,0x98,
0x99,0x99,0x99,0x91,0x11,0x19,0x99,0x99,
,

// 4: 2:2
0x11,0x19,0x99,0x99,0x99,0x99,0x99,0x11,
0x11,0x99,0x91,0x99,0x91,0x11,0x19,0x99,
0x11,0x11,0x19,0x91,0x11,0x11,0x19,0x11,
0x11,0x11,0x11,0x91,0x11,0x11,0x19,0x11,
0x99,0x99,0x99,0x99,0x11,0x11,0x19,0x99,
0x11,0x11,0x11,0x19,0x11,0x11,0x91,0x99,
0x11,0x11,0x11,0x99,0x11,0x11,0x91,0x11,
0x99,0x99,0x91,0x11,0x19,0x99,0x19,0x99,
0x91,0x19,0x11,0x11,0x19,0x19,0x91,0x19,
0x11,0x91,0x99,0x99,0x99,0x91,0x11,0x91,
0x11,0x11,0x11,0x99,0x11,0x11,0x19,0x91,
0x11,0x99,0x99,0x91,0x11,0x11,0x11,0x99,
0x11,0x91,0x19,0x99,0x99,0x99,0x99,0x11,
0x11,0x11,0x19,0x99,0x11,0x11,0x19,0x99,
0x11,0x91,0x19,0x99,0x91,0x11,0x19,0x91,
0x11,0x11,0x99,0x11,0x11,0x11,0x91,0x11,
,

// 5: 2:240
0x98,0x88,0x98,0x98,0x98,0x98,0x89,0x88,
0x99,0x99,0x98,0x99,0x88,0x88,0x88,0x89,
0x19,0x91,0x19,0x98,0x88,0x98,0x89,0x88,
0x11,0x11,0x98,0x99,0x99,0x89,0x99,0x88,
0x11,0x11,0x19,0x98,0x89,0x99,0x88,0x99,
0x19,0x19,0x11,0x99,0x99,0x88,0x98,0x88,
0x11,0x11,0x99,0x99,0x88,0x98,0x99,0x98,
0x11,0x99,0x11,0x11,0x98,0x88,0x98,0x88,
0x11,0x19,0x11,0x11,0x98,0x88,0x99,0x88,
0x11,0x11,0x19,0x99,0x99,0x98,0x88,0x88,
0x99,0x99,0x99,0x11,0x91,0x11,0x18,0x98,
0x11,0x91,0x11,0x91,0x11,0x19,0x89,0x98,
0x99,0x99,0x99,0x91,0x11,0x11,0x98,0x98,
0x11,0x11,0x19,0x11,0x11,0x11,0x18,0x98,
0x11,0x11,0x19,0x11,0x99,0x99,0x88,0x88,
0x99,0x99,0x99,0x91,0x11,0x19,0x99,0x98,
,

// 6: 2:1,2:23
0x89,0x88,0x88,0x98,0x88,0x89,0x98,0x88,
0x99,0x98,0x88,0x98,0x98,0x88,0xc9,0x99,
0x98,0x98,0x98,0x99,0x98,0x89,0x91,0x99,
0x89,0x88,0x89,0x89,0x99,0x88,0x99,0x19,
0x89,0x99,0x89,0x88,0x89,0x9c,0x99,0x91,
0x99,0x99,0x98,0x88,0x89,0x9c,0x99,0x91,
0x88,0x99,0x89,0x88,0x98,0x88,0xc9,0x99,
0x88,0x88,0x89,0x88,0x88,0x89,0x9c,0x9c,
0x88,0x88,0x89,0x98,0xcc,0xc9,0x99,0xc8,
0x89,0x88,0x98,0x89,0x19,0x99,0x19,0x9c,
0x89,0x88,0x88,0x91,0x9c,0xc9,0x99,0x1c,
0x89,0x98,0x99,0x9c,0xcc,0x89,0x99,0x1c,
0x98,0x99,0xc1,0xcc,0x88,0x89,0x99,0x1c,
0x89,0x9c,0x19,0xc8,0x88,0x89,0x99,0x9c,
0x89,0x8c,0x1c,0x88,0x89,0x89,0x99,0x9c,
0x88,0xc9,0x9c,0x89,0x98,0x89,0x99,0x9c,
,

// 7: 2:1,2:24
0x88,0x88,0x88,0x98,0x88,0x89,0x98,0x98,
0x99,0xc8,0x88,0x98,0x98,0x88,0x88,0x88,
0x99,0x98,0x98,0x99,0x98,0x89,0x88,0x89,
0x19,0xc8,0x89,0x89,0x99,0x89,0x89,0x88,
0xc8,0xcc,0x89,0x88,0x89,0x99,0x99,0x89,
0x89,0x9c,0x88,0x88,0x89,0x99,0x88,0x89,
0x89,0xc8,0x89,0x88,0x98,0x89,0x89,0x98,
0x89,0x88,0x89,0x88,0x88,0x89,0x88,0x98,
0x89,0x88,0x8c,0x98,0x88,0x88,0x99,0x88,
0x89,0x88,0x88,0x8c,0x98,0x98,0x89,0x98,
0x89,0x88,0x88,0xcc,0xc8,0x89,0x88,0x88,
0x89,0x88,0x88,0xc9,0x9c,0x99,0x89,0x99,
0x89,0x88,0x88,0xc9,0x99,0xc8,0x98,0x98,
0x89,0x88,0x88,0x8c,0x99,0x9c,0x98,0x99,
0x89,0x88,0x88,0x88,0xc9,0x9c,0x88,0x88,
0x89,0x88,0x89,0x89,0x8c,0x19,0x98,0x98,
,

// 8: 2:1,2:31
0x89,0xc9,0x98,0x99,0x99,0x18,0x99,0x9c,
0x9c,0x99,0x98,0x99,0x89,0x91,0x89,0x9c,
0x9c,0x19,0x1c,0x89,0x99,0x99,0x81,0x9c,
0x89,0x19,0x91,0x98,0x99,0x99,0x88,0x88,
0x89,0x91,0x99,0x19,0x89,0x99,0x88,0x99,
0x99,0x99,0x99,0x91,0x19,0x88,0x99,0x99,
0x89,0xcc,0xc9,0x19,0x91,0x19,0x98,0x99,
0x8c,0xcc,0x19,0x99,0x99,0x91,0x19,0x11,
0x8c,0xcc,0x9c,0x99,0x99,0x99,0x99,0x99,
0x89,0x89,0x9c,0x91,0xc9,0xc9,0x9c,0xcc,
0x89,0x8c,0xcc,0x99,0xc9,0xcc,0x9c,0x8c,
0x89,0x98,0xc8,0xcc,0xcc,0xcc,0x19,0xc9,
0x98,0x99,0x88,0x9c,0x91,0x9c,0x99,0xcc,
0x89,0x99,0x99,0x8c,0x91,0xcc,0x9c,0xc9,
0x89,0x89,0x99,0x98,0x89,0x88,0xc8,0x19,
0x88,0x88,0x98,0x88,0x98,0x8c,0x8c,0x99,
,

// 9: 2:1,2:32
0x89,0x88,0x99,0x89,0x9c,0x99,0x8c,0x88,
0x8c,0x81,0x99,0x88,0x99,0x99,0x88,0x88,
0x89,0x18,0x99,0x99,0x99,0x19,0x88,0x88,
0x88,0x99,0x99,0x99,0x91,0x99,0x88,0x88,
0x89,0x99,0x99,0x89,0x19,0x9c,0x8c,0x88,
0x99,0x99,0x88,0x11,0x99,0x8c,0xc8,0x88,
0x88,0x89,0x11,0x99,0x98,0x88,0x98,0x88,
0x19,0x19,0x99,0xcc,0x9c,0x9c,0x98,0x88,
0x99,0x9c,0xcc,0x8c,0x9c,0x99,0x88,0x88,
0xcc,0x88,0x88,0xc8,0x99,0x98,0x88,0x88,
0x98,0xc9,0xcc,0x98,0xc9,0x98,0x88,0x88,
0x9c,0x99,0x99,0x9c,0x88,0x88,0x89,0x99,
0x9c,0x99,0x9c,0x9c,0x88,0x88,0x88,0x98,
0xc8,0x99,0xc8,0x9c,0x88,0x88,0x98,0x99,
0x98,0x99,0xc8,0x88,0x88,0x98,0x98,0x88,
0x9c,0xcc,0x88,0x88,0x98,0x99,0x98,0x98,
,

// a: 2:228,1:437
0x91,0x11,0x88,0x88,0x88,0x99,0x99,0x19,
0x98,0x88,0x88,0x98,0x98,0x88,0x18,0x89,
0x88,0x88,0x99,0x99,0x99,0x88,0x89,0x89,
0x88,0x88,0x89,0x99,0x98,0x88,0x89,0x88,
0x88,0x88,0x98,0x99,0x89,0x88,0x88,0x99,
0x88,0x88,0x88,0x98,0x88,0x88,0x88,0x88,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x98,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x88,0x88,0x88,0x88,0x88,0x88,0x89,0x88,
0x89,0x88,0x88,0x88,0x88,0x88,0x98,0x99,
0x88,0x99,0x98,0x8c,0x89,0x99,0x99,0x98,
0x89,0x99,0x98,0xcc,0x89,0x99,0x99,0x99,
0x89,0x99,0x8c,0xcc,0x88,0x99,0x99,0x98,
0x89,0x99,0x9c,0x8c,0xc9,0x99,0x98,0x88,
0x88,0x89,0x99,0x99,0x99,0x99,0x99,0x98,
,

// b: 2:228
0x91,0x11,0x91,0x99,0x99,0x99,0x99,0x19,
0x98,0x98,0x89,0x11,0x11,0x11,0x18,0x89,
0x88,0x88,0x99,0x99,0x99,0x99,0x89,0x89,
0x89,0x88,0x89,0x88,0x99,0x89,0x99,0x88,
0x99,0x99,0x88,0x88,0x89,0x99,0x98,0x99,
0x89,0x98,0x88,0x88,0x99,0x88,0x99,0x88,
0x88,0x99,0x89,0x89,0x98,0x99,0x99,0x98,
0x88,0x88,0x89,0x88,0x88,0x88,0x98,0x88,
0x88,0x88,0x88,0x88,0x98,0x88,0x99,0x88,
0x89,0x88,0x98,0x98,0x98,0x99,0x89,0x88,
0x89,0x88,0x98,0x98,0x88,0x89,0x98,0x99,
0x88,0x98,0x99,0x99,0x99,0x99,0x99,0x98,
0x88,0x99,0x88,0x88,0x88,0x98,0x88,0x99,
0x89,0x99,0x99,0x98,0x98,0x88,0x98,0x98,
0x89,0x99,0x98,0x98,0x99,0x98,0x98,0x88,
0x88,0x88,0x88,0x98,0x88,0x99,0x99,0x98,
,

// c: 2:237
0x89,0x11,0x91,0x99,0x99,0x99,0x99,0x11,
0x88,0x88,0x89,0x11,0x11,0x11,0x19,0x99,
0x88,0x88,0x98,0x99,0x99,0x99,0x99,0x11,
0x89,0x88,0x88,0x11,0x11,0x11,0x19,0x11,
0x99,0x99,0x88,0x99,0x99,0x99,0x99,0x11,
0x89,0x98,0x88,0x91,0x11,0x19,0x11,0x11,
0x98,0x98,0x99,0x89,0x91,0x99,0x99,0x11,
0x88,0x88,0x89,0x88,0x81,0x11,0x11,0x99,
0x88,0x88,0x88,0x88,0x89,0x11,0x11,0x91,
0x99,0x88,0x88,0x98,0x98,0x88,0x89,0x91,
0x99,0x88,0x98,0x98,0x88,0x89,0x88,0x91,
0x88,0x98,0x99,0x99,0x99,0x99,0x99,0x91,
0x88,0x99,0x88,0x88,0x88,0x98,0x89,0x11,
0x89,0x99,0x98,0x98,0x98,0x88,0x99,0x99,
0x89,0x89,0x98,0x98,0x99,0x98,0x98,0x91,
0x88,0x88,0x98,0x98,0x88,0x99,0x99,0x88,
,

// d: 2:1,2:121
0x89,0x88,0x88,0x98,0x88,0x89,0x98,0x98,
0x99,0x98,0x88,0x98,0x98,0x88,0x88,0x88,
0x98,0x98,0x98,0x99,0x98,0x89,0x88,0x89,
0x89,0x88,0x89,0x89,0x99,0x89,0x89,0x88,
0x89,0x99,0x89,0x88,0x89,0x99,0x99,0x89,
0x99,0x99,0x98,0x88,0x89,0x99,0x88,0x89,
0x88,0x99,0x89,0x88,0x98,0x89,0x89,0x98,
0x88,0x88,0x89,0x88,0x88,0x89,0x88,0x98,
0x88,0x88,0x89,0x98,0x88,0x88,0x99,0x88,
0x89,0x88,0x98,0x88,0x98,0x98,0x89,0x98,
0x89,0x88,0x88,0x98,0x88,0x89,0x88,0x88,
0x89,0x98,0x99,0x98,0x98,0x99,0x89,0x99,
0x98,0x99,0x88,0x98,0x98,0x98,0x98,0x98,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x89,0x99,0x99,0x99,0x99,0x99,0x99,0x99,
0x89,0x99,0x99,0x99,0x99,0x99,0x99,0x99,
,

// e: 2:1,1:437,2:122
0x89,0x88,0x88,0x88,0x88,0x89,0x98,0x98,
0x99,0x88,0x88,0x98,0x98,0x88,0x88,0x88,
0x98,0x88,0x99,0x99,0x99,0x88,0x88,0x89,
0x88,0x88,0x89,0x99,0x98,0x88,0x89,0x88,
0x88,0x88,0x98,0x99,0x89,0x88,0x88,0x89,
0x88,0x88,0x88,0x98,0x88,0x88,0x88,0x89,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x98,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x98,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x88,0x88,0x88,0x88,0x88,0x88,0x89,0x98,
0x89,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x89,0x99,0x98,0x8c,0x89,0x99,0x99,0x99,
0x99,0x99,0x98,0xcc,0x89,0x99,0x99,0x98,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x89,
0x99,0x99,0x99,0x99,0x99,0x99,0x99,0x98,
0x99,0x99,0x99,0x99,0x99,0x99,0x99,0x98,
,

// f: 2:236,2:129
0x9c,0x99,0x99,0x99,0x99,0x99,0x99,0x99,
0x9c,0x99,0x99,0x99,0x99,0x99,0x99,0x99,
0x88,0x88,0x9c,0x99,0x88,0x99,0x89,0x89,
0x89,0x88,0x8c,0xc8,0x99,0x99,0x99,0x88,
0x9c,0x9c,0xc9,0xcc,0xcc,0xcc,0xcc,0xcc,
0x8c,0xcc,0xc9,0xc9,0x99,0x99,0xc9,0xc9,
0x8c,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,
0x8c,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,
0x8c,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,
0x8c,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,
0x8c,0xcc,0x88,0x88,0x88,0x88,0x88,0x88,
0x88,0xcc,0xc8,0x88,0x88,0x88,0x88,0x88,
0x88,0x9c,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,
0x89,0x9c,0x88,0x88,0x88,0x88,0x88,0x88,
0x8c,0xc8,0x89,0x11,0x99,0x98,0x99,0x98,
0x99,0x99,0x99,0x91,0x11,0x19,0x99,0x99,
,

// 10: 2:236,2:130
0x99,0x99,0x99,0x99,0x99,0x99,0x99,0x98,
0x99,0x99,0x99,0x99,0x99,0x99,0x99,0x99,
0x88,0x88,0x99,0x99,0x88,0xcc,0x89,0x89,
0x89,0x88,0x89,0x88,0x99,0xcc,0x99,0x88,
0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0x99,0xc9,
0x9c,0x99,0x99,0xc9,0x99,0xcc,0xc9,0x98,
0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xc8,
0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xc8,
0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xc8,
0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xc8,
0x88,0x88,0x88,0x88,0x88,0x88,0x88,0x88,
0x88,0x88,0x88,0x88,0x88,0x88,0xcc,0x88,
0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xcc,0xc8,
0x88,0x88,0x88,0x88,0x88,0x88,0x8c,0xc8,
0x89,0x91,0x99,0x11,0x99,0x98,0x9c,0xc8,
0x99,0x99,0x99,0x91,0x11,0x19,0x99,0x99,
,

};
