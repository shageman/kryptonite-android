package co.krypt.kryptonite.pairing;

import co.krypt.kryptonite.log.SSHSignatureLog;

/**
 * Created by Kevin King on 12/18/16.
 * Copyright 2016. KryptCo, Inc.
 */

public class Session {
    public final Pairing pairing;
    public final SSHSignatureLog lastCommand;
    public final boolean approved;
    public final Long approvedUntilUnixSeconds;

    public Session(Pairing pairing, SSHSignatureLog lastCommand, boolean approved, Long approvedUntilUnixSeconds) {
        this.pairing = pairing;
        this.lastCommand = lastCommand;
        this.approved = approved;
        this.approvedUntilUnixSeconds = approvedUntilUnixSeconds;
    }
}
