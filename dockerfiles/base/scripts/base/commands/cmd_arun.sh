#!/bin/bash
# Copyright (c) 2012-2016 Codenvy, S.A.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#   Tyler Jewell - Initial Implementation
#

pre_cmd_arun() {
  if [ ! -d /archetype/$ASSEMBLY_ID ]; then
    error "Assembly at ${ARCHETYPE_MOUNT}/$ASSEMBLY_ID not found."
    return 2
  fi
}

cmd_arun() {

  cd /archetype/$ASSEMBLY_ID

  GENERATE_COMMAND="docker run -it --rm --name run-che \
                   -v /var/run/docker.sock:/var/run/docker.sock \
                   -v \"${DATA_MOUNT}\":${CHE_CONTAINER_ROOT} \
                   -v \"${ARCHETYPE_MOUNT}\"/$ASSEMBLY_ID/assembly-main/target/eclipse-che-$ASSEMBLY_VERSION/eclipse-che-$ASSEMBLY_VERSION:/assembly \
                   -w /home/user/che-build \
                      ${CHE_IMAGE_FULLNAME} \
                          start --skip:nightly"
  log ${GENERATE_COMMAND}
  eval ${GENERATE_COMMAND}
}