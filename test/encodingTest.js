const printHex = (buffer) => {
    let outBuffer = [];
    buffer.forEach( (val) =>{
        let str = val.toString(16).toUpperCase()
        outBuffer.push("0".repeat(2 - str.length) + str);
    });
    let output = outBuffer.join(" ");
    console.log(output);
}


const ieee754 = (n) => {
    const buf = Buffer.allocUnsafe(4);
    buf.writeFloatLE(n, 0);
    return Uint8Array.from(buf);
};

const encodeString = (str) => [
    str.length,
    ...str.split("").map(s => s.charCodeAt(0))
];

const signedLEB128 = (n) => {
    const buffer = [];
    let more = true;
    while (more) {
        let byte = n & 0x7f;
        n >>>= 7;
        if ((n === 0 && (byte & 0x40) === 0) || (n === -1 && (byte & 0x40) !== 0)) {
            more = false;
        } else {
            byte |= 0x80;
        }
        buffer.push(byte);
    }
    return buffer;
};

const unsignedLEB128 = (n) => {
    const buffer = [];
    do {
        let byte = n & 0x7f;
        n >>>= 7;
        if (n !== 0) {
            byte |= 0x80;
        }
        buffer.push(byte);
    } while (n !== 0);
    return buffer;
};

printHex(signedLEB128(43));
printHex(signedLEB128(536));
printHex(signedLEB128(123));
printHex(signedLEB128(2345));
console.log("");

printHex(signedLEB128(-42));
printHex(signedLEB128(-355632));
printHex(signedLEB128(-232314));
printHex(signedLEB128(-53334234));
console.log("");

printHex(unsignedLEB128(44));
printHex(unsignedLEB128(9999));
printHex(unsignedLEB128(66345));
printHex(unsignedLEB128(2335533));
console.log("");

printHex(encodeString("asm"));
printHex(encodeString("jano"));
printHex(encodeString("anton"));
printHex(encodeString("patrik"));
console.log("");

printHex(ieee754(42.69));
printHex(ieee754(4223.69));
printHex(ieee754(426.643));
printHex(ieee754(4532.6449));