import static org.junit.jupiter.api.Assertions.*;

import com.wzl.tank.Dir;
import com.wzl.tank.Group;
import com.wzl.tank.Tank;
import com.wzl.tank.netty.TankJoinMsg;
import com.wzl.tank.netty.TankJoinMsgDecoder;
import com.wzl.tank.netty.TankJoinMsgEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Codec Unit Testing
 */
public class TankJoinMsgTest {
    @Test
    void testEncoder() {
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.DOWN, true, Group.BAD
        ,id);
        ch.pipeline()
                .addLast(new TankJoinMsgEncoder());

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf) ch.readOutbound();

        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Dir dir = Dir.values()[dirOrdinal];
        boolean moving =buf.readBoolean();
        int groupOrdinal = buf.readInt();

        Group g = Group.values()[groupOrdinal];
        UUID uuid = new UUID(buf.readLong(), buf.readLong());

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(Dir.DOWN, dir);
        assertEquals(true, moving);
        assertEquals(Group.BAD, g);
        assertEquals(id, uuid);
    }

    @Test
    void testDecoder() {
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.DOWN, true, Group.BAD
                ,id);
        ch.pipeline()
                .addLast(new TankJoinMsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(msg.toBytes());
        ch.writeInbound(buf.duplicate());
        TankJoinMsg msgR = (TankJoinMsg) ch.readInbound();

        assertEquals(5, msgR.x);
        assertEquals(10, msgR.y);
        assertEquals(Dir.DOWN, msgR.dir);
        assertEquals(Group.BAD, msgR.group);
        assertEquals(id, msgR.id);
    }
}