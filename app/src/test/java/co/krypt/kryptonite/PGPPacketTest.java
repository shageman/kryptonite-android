package co.krypt.kryptonite;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import co.krypt.kryptonite.pgp.packet.InvalidUTF8Exception;
import co.krypt.kryptonite.pgp.packet.OldPacketLengthType;
import co.krypt.kryptonite.pgp.packet.PacketHeader;
import co.krypt.kryptonite.pgp.packet.PacketTag;
import co.krypt.kryptonite.pgp.packet.PacketType;
import co.krypt.kryptonite.pgp.packet.UserIDPacket;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PGPPacketTest {
    @Test(expected = InvalidUTF8Exception.class)
    public void invalidUserIDPacket_throwsIO() throws Exception {
        PacketHeader header = new PacketHeader(
                PacketTag.parse((byte) (PacketTag.LEADING_ONE | OldPacketLengthType.ONE_OCTET.getValue() | (PacketType.USER_ID.getValue() << 2))),
                2
        );
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(new byte[]{(byte) 0xc3, 0x28}));
        UserIDPacket.parse(header, in);
    }
}