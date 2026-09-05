#!/usr/bin/env python

""" Package the war with maven. The explicit processor passes its input
files as arguments; maven would read those as goals, so they are ignored and
the fixed goal list runs instead. """

import subprocess
import sys


def main():
    """ main entry point """
    sys.exit(subprocess.call(["mvn", "-B", "-q", "-DskipTests", "package"]))


if __name__ == "__main__":
    main()
